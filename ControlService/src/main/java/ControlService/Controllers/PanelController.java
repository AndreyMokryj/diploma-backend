package ControlService.Controllers;

import ControlService.Entities.PanelE;
import ControlService.Entities.StateE;
import ControlService.Repositories.PanelRepository;
import ControlService.Repositories.StateRepository;
import ControlService.Repositories.UserRepository;
import ControlService.vo.PanelVO;
import ControlService.vo.StateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/panels")
@Component
public class PanelController {
    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private StateRepository stateRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/")
    public @ResponseBody
    Iterable<PanelE> getAll() {
        return panelRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/userId/{userId}")
    public @ResponseBody
    Iterable<PanelE> getByUserId(@PathVariable String userId) {
        return panelRepository.findByUserId(userId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="/{id}")
    public @ResponseBody
    PanelE getById(@PathVariable String id) {
        Optional<PanelE> panel = panelRepository.findById(id);
        return panel.get();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path="/{id}")
    public @ResponseBody
    void updateById(@PathVariable String id, @RequestBody PanelVO panelVO) {
        PanelE panel = PanelE.fromVO(panelVO);
        panelRepository.save(panel);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/reduce/{panelId}")
    public void reduceForPanel(@PathVariable String panelId) {
        int k = 10;

        List<StateE> states = (List<StateE>) stateRepository.findByPanelId(panelId);
        PanelE panel = getById(panelId);
        for (StateE state : states) {
            state.setAzPlus(state.getAzPlus() / k);
            state.setAzMinus(state.getAzMinus() / k);
            state.setAltPlus(state.getAltPlus() / k);
            state.setAltMinus(state.getAltMinus() / k);
        }

        stateRepository.saveAll(states);

    }

    private StateE getState(StateVO stateVO) {
        StateE state = StateE.fromVO(stateVO);
        try {
            Optional<StateE> state1 = stateRepository.findByParams(
                    state.getPanelId(),
                    state.getAzimuth(),
                    state.getAltitude()
            );
            return state1.get();
        }
        catch (Exception ex){
            state.setId(UUID.randomUUID().toString());
            StateE saved = stateRepository.save(state);
            return saved;
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/prepare/{panelId}")
    public void preparePanel(@PathVariable String panelId) {
        PanelE panel = getById(panelId);

        StateVO stateVO = new StateVO();
        stateVO.setPanelId(panelId);
        stateVO.setAzimuth(panel.getAzimuth());
        stateVO.setAltitude(panel.getAltitude());

        StateE currentState = getState(stateVO);
        currentState.setAzPlus(0.1);
        currentState.setAzMinus(0);
        currentState.setAltPlus(0);
        currentState.setAltMinus(0);
        stateRepository.save(currentState);

        stateVO.setAzimuth((stateVO.getAzimuth() + 1) % 360);
        StateE nextState = getState(stateVO);
        nextState.setAzPlus(0);
        nextState.setAzMinus(0);
        nextState.setAltPlus(0);
        nextState.setAltMinus(0);
        stateRepository.save(nextState);
    }

    //Requests from app
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path="/app/userId/{userId}")
    public @ResponseBody
    Iterable<Object> getByUserIdA(@PathVariable String userId) {
        String sid = userRepository.findSID(userId);
        List<Object> response = restTemplate.exchange("http://" + sid + "/panels/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Object>>() {}).getBody();

//        return panelRepository.findByUserId(userId);
        return response;
    }

}

package com.consumingapi.cepapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.consumingapi.cepapi.dto.CepDto;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cep")
@CrossOrigin(origins = "*")
public class CepController {
    
    @GetMapping
    public String showForms() {
        return "index";
    }

    @PostMapping
    //REQUESTPARAM acessa a requisição /cep
    public String getCep(@RequestParam String cep, Model model) {
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        //Resolve a comunicação HTTP, fazendo requisições
        RestTemplate restTemplate = new RestTemplate();
        //O getForObject() retorna a resposta como um objeto da classe CepDto
        CepDto cepDto = restTemplate.getForObject(url, CepDto.class);
        //Envia os dados para HTML 
        //"cep" é o nome do objeto cepDto que será usado para acessar os dados no HTML/JSP
        model.addAttribute("cep", cepDto);
        return "index";
    }
    
}

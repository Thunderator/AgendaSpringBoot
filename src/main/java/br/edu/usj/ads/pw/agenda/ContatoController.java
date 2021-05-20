package br.edu.usj.ads.pw.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {

    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping(value = "/")
    public ModelAndView getIndex() {
        List<Contato> lista = new ArrayList<>();

        lista = contatoRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("lista", lista);

        return modelAndView;
    }

    @GetMapping(value = "/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id) {
        // ler contato do banco pelo id
        Contato contato = new Contato();
        contato = contatoRepository.findById(id).get();

        // instanciar o template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // preencher o template com o contato selecionado
        modelAndView.addObject("contato", contato);

        // retornar o template
        return modelAndView;
    }

    @GetMapping(value = "/cadastro")
    public ModelAndView getCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        return modelAndView;
    }

    @PostMapping(value = "/adicionar")
    public ModelAndView postAdicionar(@RequestParam String nome, @RequestParam String tipo,
            @RequestParam String telefone) {
                Contato contato = new Contato();

            contato.setNome(nome);
            contato.setTipo(tipo);
            contato.setTelefone(telefone);

            contatoRepository.save(contato);

            ModelAndView modelAndView = new ModelAndView("detalhes");

            modelAndView.addObject("contato", contato);
        return modelAndView;
    }
}
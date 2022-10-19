package br.ifpe.web3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.ifpe.web3.model.Contato;

@Controller
public class ContatoController {
	
	
	private Contato dao;
	
	private List<Contato> contatos = new ArrayList<>();

	@GetMapping("/exibirContato")
	public String exibirForm(Contato contato) {
		return "contatos-form";
	}
	
	@PostMapping("/salvarContato")
	public String salvarContato(Contato contato) {
		dao.save(contato);
		return "redirect:/listarContatos";
	}
	
	@GetMapping("/listarContatos")
	public String listarContatos(Model model) {
		model.addAttribute("lista", dao.findAll());
		return "contatos-list";
	}
	
	@GetMapping("/removerContato")
	public String removerContato(Integer codigo) {
		dao.deleteById(codigo);
		return "redirect:/listarContatos";
	}
	
	@GetMapping("/editarContato")
	public String editarContato(Integer codigo, Model model) {
		Contato contato = dao.findById(codigo).orElse(Null);
		model.addAttribute("contato", contato);
		return "contatos-form";
	}
}

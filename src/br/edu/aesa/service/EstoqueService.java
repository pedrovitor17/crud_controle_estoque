package br.edu.aesa.service;

import br.edu.aesa.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EstoqueService {
    private final Map<Integer, Produto> armazenagem = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public Produto cadastrar(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo.");
        if (produto.getNome() == null || produto.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do produto é obrigatório.");

        int id = produto.getId();
        if (id > 0) {
            if (armazenagem.containsKey(id)) throw new IllegalArgumentException("ID já existe: " + id);
        } else {
            id = nextId.getAndIncrement();
            produto.setId(id);
        }

        armazenagem.put(id, produto);
        return produto;
    }

    public Produto buscarPorId(int id) {
        return armazenagem.get(id);
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(armazenagem.values());
    }

    public boolean atualizar(int id, Produto novosDados) {
        Produto existente = armazenagem.get(id);
        if (existente == null) return false;

        if (novosDados.getNome() != null && !novosDados.getNome().trim().isEmpty())
            existente.setNome(novosDados.getNome());
        if (novosDados.getCategoria() != null)
            existente.setCategoria(novosDados.getCategoria());
        if (novosDados.getPreco() >= 0)
            existente.setPreco(novosDados.getPreco());
        if (novosDados.getQuantidade() >= 0)
            existente.setQuantidade(novosDados.getQuantidade());

        armazenagem.put(id, existente);
        return true;
    }

    public boolean remover(int id) {
        return armazenagem.remove(id) != null;
    }
}

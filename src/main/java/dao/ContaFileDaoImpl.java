package dao;

import dominio.Conta;
import dominio.Usuario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContaFileDaoImpl implements ContaFileDao{
    private List<Conta> registroLinhas = new ArrayList<>();

    @Override
    public void preparaArquivo(String tipoConta) {
        try (Stream<String> streamLinhas = Files.lines(Path.of("src\\main\\"+tipoConta+".txt"))) {
            registroLinhas = streamLinhas
                    .filter(Predicate.not(String::isEmpty))
                    .map(Conta::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravaArquivo(Conta conta) {
        String tipoConta = conta.getTipoConta();
        var leArquivo = new File("src\\main\\"+tipoConta+".txt");
        Conta conteudo;
        try{
            var arquivo = new FileWriter (leArquivo, false);
            arquivo.flush();
            for (Conta registroLinha : registroLinhas) {
                conteudo = registroLinha;
                arquivo.write(String.valueOf(conteudo+"\n"));
            }
            arquivo.close();
        }catch (IOException e)  {
            e.printStackTrace();
        }
    }

    @Override
    public void criarConta(Usuario usuario, Conta conta) {
        String tipoConta = conta.getTipoConta();
        if (!validarConta(conta, usuario)) {
            preparaArquivo(conta.getTipoConta());
            registroLinhas.add(conta);
            gravaArquivo(conta);
            System.out.println("Conta criada com sucesso!");
        } else {
            System.err.println("A conta já está ativa!");
        }
    }

    @Override
    public Conta getConta(String nome, String tipoConta) {
        preparaArquivo(tipoConta);
        for (Conta registroLinha : registroLinhas) {
            if (registroLinha.getUsuario().equals(nome)){
                return registroLinha;
            }
        }
        System.err.println("A conta selecionada ainda não foi criada!");
        return null;
    }

    @Override
    public boolean validarConta(Conta conta, Usuario usuario){
        preparaArquivo(conta.getTipoConta());
        for (Conta registroLinha : registroLinhas) {
            if (registroLinha.getUsuario().equals(usuario.getCpf())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void alterarConta(String tipoConta, Conta conta){
        for (Conta registroLinha : registroLinhas) {
            if (registroLinha.getUsuario().equals(conta.getUsuario())) {
                conta.setSaldo(conta.getSaldo());
                gravaArquivo(conta);
                return;
            }
        }
        System.err.println("Nenhuma conta encontrada!");
    }
}

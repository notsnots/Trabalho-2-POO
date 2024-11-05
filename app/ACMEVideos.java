package app;

import dados.Acervo;
import dados.Video;
import dados.Filme;
import dados.Seriado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.ArrayList;

public class ACMEVideos {
    private Acervo acervo;

    public ACMEVideos() {
        acervo = new Acervo();
    }

    public void processar() {
        this.leitorArquivos();
        this.videoTituloMaisLongo();
        this.videoMenorCusto();
        this.seriadoMaiorExibicao();
        this.diretorComMaisFilmes();
    }

    public void leitorArquivos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosentrada.txt"))) {
            String linha;
            StringBuilder conteudo = new StringBuilder();

            while ((linha = reader.readLine()) != null) {

                if(linha.contains(",")){
                    continue;
                }
                linha = linha.trim();
                String[] divisao = linha.split(";");
                int tipo = Integer.parseInt(divisao[0]);
                int codigo = Integer.parseInt(divisao[1]);
                String titulo = divisao.length > 2 ? divisao[2] : "Titulo Desconhecido";

                if (tipo == 1 && divisao.length >= 5) {
                    String diretor = divisao[3];
                    double duracao = Double.parseDouble(divisao[4]);
                    Filme filme = new Filme(codigo, titulo, diretor, duracao);
                    if(acervo.addVideo(filme)){
                        acervo.addVideo(filme);
                        conteudo.append("1:").append(filme.geraTexto()).append("\n");
                    }else{
                        System.out.println("1:Erro - codigo de video repetido\n");
                    }
                    
                } else if (tipo == 2 && divisao.length >= 6) {
                    int anoInicio = Integer.parseInt(divisao[3]);
                    int anoFim = Integer.parseInt(divisao[4]);
                    int episodios = Integer.parseInt(divisao[5]);
                    Seriado serie = new Seriado(codigo, titulo, anoInicio, anoFim, episodios);
                    if(acervo.addVideo(serie)){
                        acervo.addVideo(serie);
                        conteudo.append("1:").append(serie.geraTexto()).append("\n");
                    }else{
                        conteudo.append("1:Erro - codigo de video repetido\n");
                    }
                }
            }

            Files.write(Paths.get("relatorio.txt"), conteudo.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter valor numérico: " + e.getMessage());
        }
    }

    public void videoTituloMaisLongo(){
        Video v = acervo.videoMaiorTitulo();
        StringBuilder saida = new StringBuilder();
        try{
            if(v == null){
                saida.append("2:Erro - nenhum vídeo cadastrado.\n");
            }
            else{
                saida.append("2:").append(v.getCodigo()).append("-").append(v.getTitulo()).append("\n");
            }
            Files.write(Paths.get("relatorio.txt"), saida.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void videoMenorCusto(){
        Video v = acervo.videoMenorCusto();
        StringBuilder saida = new StringBuilder();
        try{
            if(v == null){
                saida.append("3:Erro - nenhum vídeo cadastrado.\n");
            }
            else{
                saida.append("3:").append(v.getCodigo()).append("-").append(v.getTitulo()).append("-").append(String.format("%.2f", v.calculaCusto())).append("\n");
            }
            Files.write(Paths.get("relatorio.txt"), saida.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void seriadoMaiorExibicao(){
        Seriado serie = acervo.seriadoMaiorExibicao();
        StringBuilder saida = new StringBuilder();
        try{
            if(serie == null){
                saida.append("4:Erro - nenhum seriado cadastrado.\n");
            }
            else{
                saida.append("4:").append(serie.getCodigo()).append("-").append(serie.getTitulo()).append("-").append(serie.calculaTempoExibicao()).append("\n");
            }
            Files.write(Paths.get("relatorio.txt"), saida.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void diretorComMaisFilmes(){
        String diretor = acervo.diretorMaisFilmes();
        StringBuilder saida = new StringBuilder();
        try{
            if (diretor.equals("Erro - nenhum filme cadastrado")) {
                saida.append("5:Erro - nenhum filme cadastrado\n");
            } else {
                saida.append("5:").append(diretor).append("\n");
            }
            Files.write(Paths.get("relatorio.txt"), saida.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

package dados;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Acervo {

	private ArrayList<Video> video;

	public Acervo() {
		this.video = new ArrayList<>();
	}

	public boolean addVideo(Video v) {
		for(Video i: video){
			if(v.getCodigo() == (i.getCodigo())){
				return false;
			}
		}
		video.add(v);
		return true;
	}

	public Video videoMaiorTitulo(){
		return video
				.stream()
				.max(Comparator.comparingInt(video -> video.getTitulo().length()))
				.orElse(null);
	}

	public Video videoMenorCusto(){
		return video
				.stream()
				.max(Comparator.comparingDouble(Imprimivel::calculaCusto))
				.orElse(null);
	}

	public Seriado seriadoMaiorExibicao(){
		return video
				.stream()
				.filter(video -> video instanceof Seriado)
				.map(video -> (Seriado) video)
				.max(Comparator.comparingInt(Seriado::calculaTempoExibicao))
				.orElse(null);
	}

	public String diretorMaisFilmes() {
		return video.stream()
				.filter(video -> video instanceof Filme)
				.map(video -> (Filme) video)
				.collect(Collectors.groupingBy(Filme::getDiretor, Collectors.counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(entry -> "5:" + entry.getKey() + "-" + entry.getValue())
				.orElse("5:Erro - nenhum filme cadastrado");
	}


}

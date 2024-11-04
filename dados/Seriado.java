package dados;

import java.io.IOException;

public class Seriado extends Video {

	private int anoInicio;

	private int anoFim;

	private int numEpisodios;


	public Seriado(int codigo, String titulo, int anoInicio, int anoFim, int numEpisodios){
		super(codigo, titulo);
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.numEpisodios = numEpisodios;
	}

	@Override
	public String geraTexto() {
		StringBuilder texto = new StringBuilder();
		try {
			texto.append(super.getCodigo()).append("-");
			texto.append(super.getTitulo()).append("-");
			texto.append(anoInicio).append("-");
			texto.append(anoFim).append("-");
			texto.append(numEpisodios > 0 ? numEpisodios : "0").append("-");
			texto.append(this.calculaCusto());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return texto.toString();
	}

	public int getAnoInicio() {
		return anoInicio;
	}


	public int getNumEpisodios() {
		return numEpisodios;
	}

	public int getAnoFim() {
		return anoFim;
	}

	public double calculaCusto(){
		return numEpisodios * 0.50;
	}

	public int calculaTempoExibicao(){
		return anoFim - anoInicio;
	}

}

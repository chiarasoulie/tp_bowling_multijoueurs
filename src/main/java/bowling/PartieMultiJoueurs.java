package bowling;

public class PartieMultiJoueurs extends PartieMonoJoueur implements IPartieMultiJoueurs {
	private String[] nomsDesJoueurs;


	public PartieMultiJoueurs(String[] nomsDesJoueurs){
		this.nomsDesJoueurs=nomsDesJoueurs;;
	}

	public String demarreNouvellePartie(String[] nomsDesJoueurs){
		for(var i= 0; i < nomsDesJoueurs.length; i++) {
			System.out.println("Prochain tir : joueur "+nomsDesJoueurs[i]+" , tour n° " +numeroTourCourant()
				+", boule n° "+i);
		}
	}
	
}

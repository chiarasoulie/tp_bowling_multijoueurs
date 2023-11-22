package bowling;
import java.util.HashMap;


public class PartieMultiJoueurs implements IPartieMultiJoueurs {
	private HashMap<String, PartieMonoJoueur> laPartie;
	private String[] nomsJoueurs;
	private int nombreDeJoueurs;
	private int numeroJoueurActuel=-1;

	private String phraseRetour;

	public String getPhraseRetour() {
		this.phraseRetour = "Prochain tir : joueur " + nomsJoueurs[numeroJoueurActuel] + ", tour n° " + laPartie.get(nomsJoueurs[numeroJoueurActuel]).numeroTourCourant() + ", boule n° " + laPartie.get(nomsJoueurs[numeroJoueurActuel]).numeroProchainLancer();
		return phraseRetour;
	}

	/**
	 * Démarre une nouvelle partie pour un groupe de joueurs
	 *
	 * @param nomsDesJoueurs un tableau des noms de joueurs (il faut au moins un joueur)
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 1, boule n° 1"
	 * @throws java.lang.IllegalArgumentException si le tableau est vide ou null
	 */

	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("le tableau de noms est vide");
		}

		//initialisation
		laPartie = new HashMap<>();
		this.nomsJoueurs = nomsDesJoueurs;
		nombreDeJoueurs = nomsDesJoueurs.length;
		numeroJoueurActuel = 0;

		for (String nom : nomsDesJoueurs) {
			laPartie.put(nom, new PartieMonoJoueur());
		}

		return getPhraseRetour();
	}

	/**
	 * Enregistre le nombre de quilles abattues pour le joueur courant, dans le tour courant, pour la boule courante
	 *
	 * @param nombreDeQuillesAbattues : nombre de quilles abattue à ce lancer
	 * @return une chaîne de caractères indiquant le prochain joueur,
	 * de la forme "Prochain tir : joueur Bastide, tour n° 5, boule n° 2",
	 * ou bien "Partie terminée" si la partie est terminée.
	 * @throws java.lang.IllegalStateException si la partie n'est pas démarrée.
	 */
	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
		if(numeroJoueurActuel==-1){
			throw new IllegalStateException("La partie n'a pas commencé");
		}

		if (laPartie.get((nomsJoueurs[0])).numeroTourCourant()==0) {
			return "Partie terminée";
		}

		PartieMonoJoueur joueurPartie = laPartie.get(nomsJoueurs[numeroJoueurActuel]);
		joueurPartie.enregistreLancer(nombreDeQuillesAbattues);

		if (joueurPartie.numeroProchainLancer() == 1 || joueurPartie.estTerminee()) {
			numeroJoueurActuel = (numeroJoueurActuel + 1) % nombreDeJoueurs;
		}
		return getPhraseRetour();
	}

	/**
	 * Donne le score pour le joueur playerName
	 *
	 * @param nomDuJoueur le nom du joueur recherché
	 * @return le score pour ce joueur
	 * @throws IllegalArgumentException si nomDuJoueur ne joue pas dans cette partie
	 */
	@Override
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
		if (laPartie.get(nomDuJoueur) == null) {
			throw new IllegalArgumentException(nomDuJoueur + "ne joue pas cette partie");
		}

		return laPartie.get(nomDuJoueur).score();
	}
}

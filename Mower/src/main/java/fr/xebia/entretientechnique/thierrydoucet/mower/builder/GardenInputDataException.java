package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

/**
 * Exception pour indiquer une erreur de traitement ou de formation des données
 * d'entrée de l'application
 * 
 * @author Thierry Doucet
 */
public class GardenInputDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut.
	 */
	public GardenInputDataException() {
		super();
	}

	/**
	 * Constructeur avec message.
	 */
	public GardenInputDataException(String message) {
		super(message);
	}

	/**
	 * Constructeur avec message et cause.
	 */
	public GardenInputDataException(String message, Throwable cause) {
		super(message, cause);
	}
}

package fr.xebia.entretientechnique.thierrydoucet.mower.bean;

/**
 * Enumeration représentant les différentes coordonnées géographique utilisées
 * par l'application.
 * 
 * @author Thierry Doucet
 */
public enum CardinalDirection {

	/**
	 * Nord
	 */
	N {
		@Override
		public CardinalDirection getNextAnticlockwiseDirection() {
			return W;
		}

		@Override
		public CardinalDirection getNextClockwiseDirection() {
			return E;
		}
	},

	/**
	 * Est
	 */
	E {
		@Override
		public CardinalDirection getNextAnticlockwiseDirection() {
			return N;
		}

		@Override
		public CardinalDirection getNextClockwiseDirection() {
			return S;
		}
	},

	/**
	 * Sud
	 */
	S {
		@Override
		public CardinalDirection getNextAnticlockwiseDirection() {
			return E;
		}

		@Override
		public CardinalDirection getNextClockwiseDirection() {
			return W;
		}
	},

	/**
	 * Ouest
	 */
	W {
		@Override
		public CardinalDirection getNextAnticlockwiseDirection() {
			return S;
		}

		@Override
		public CardinalDirection getNextClockwiseDirection() {
			return N;
		}
	};

	/**
	 * @return La prochaine coordonnée geographique, sens anti-horraire.
	 */
	public abstract CardinalDirection getNextAnticlockwiseDirection();

	/**
	 * @return @return La prochaine coordonnée geographique, sens horraire.
	 */
	public abstract CardinalDirection getNextClockwiseDirection();

}

package utils;

import model.ChessGame;
import model.Echiquier;

public interface Observeur {
	void update(Echiquier echiquierCourant);
}

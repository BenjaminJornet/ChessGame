package utils;

import java.util.List;

import model.ChessGame;
import model.Echiquier;
import model.PieceIHM;

public interface Observeur {
	void update(List<PieceIHM> list);
}

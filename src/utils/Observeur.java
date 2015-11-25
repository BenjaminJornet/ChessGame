package utils;

import java.util.List;

import model.PieceIHM;

public interface Observeur {
	void update(List<PieceIHM> list);
}

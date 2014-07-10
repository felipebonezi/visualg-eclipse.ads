package visualgeclipse.ads.editors;

import org.eclipse.swt.graphics.RGB;

//Definição de cores para determinados tipos de palavras
public interface IVisualGColorConstants {
	RGB COMMENT = new RGB(128, 64, 128);
	RGB RESERVED_WORD = new RGB(128, 0, 0);
	RGB STRING = new RGB(0, 128, 0);
	RGB DEFAULT = new RGB(0, 0, 0);
}

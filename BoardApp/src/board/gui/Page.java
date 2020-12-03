package board.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class Page extends JPanel{
	BoardMain boardMain;
	
	public Page(BoardMain boardMain) {
		this.boardMain = boardMain;
		this.setPreferredSize(new Dimension(880,680));
	}
}

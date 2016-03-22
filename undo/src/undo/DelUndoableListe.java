package undo;

import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;

public class DelUndoableListe extends AbstractUndoableEdit {
	private DefaultListModel<String> list;
	private String element;
	private int position;
	
	
	
	public DelUndoableListe(DefaultListModel<String> list, String element, int position) {
		super();
		this.list = list;
		this.element = element;
		this.position = position;
	}
	
	@Override
	public void undo() {
		this.list.add(position, element);
	}
	
	@Override
	public void redo() {
		this.list.remove(position);
	}


}

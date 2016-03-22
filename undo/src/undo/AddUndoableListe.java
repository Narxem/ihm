package undo;

import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;

public class AddUndoableListe extends AbstractUndoableEdit {
	private DefaultListModel<String> list;
	private String element;
	private int position;
	
	
	
	public AddUndoableListe(DefaultListModel<String> list, String element, int position) {
		super();
		this.list = list;
		this.element = element;
		this.position = position;
	}
	
	@Override
	public void undo() {
		this.list.remove(position);
	}
	
	@Override
	public void redo() {
		this.list.add(position, element);
	}

}

package ch.hszt.tierverwaltung.backend;

public interface IPublicCloneable extends Cloneable {
	/**
	 * Erstellt eine exakte Kopie des Objekts
	 * @return
	 */
	public Object clone();
}

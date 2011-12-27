package ch.hszt.tierverwaltung.backend;

/**
 * 
 * @author Aschi
 *
 */
public interface IPublicCloneable extends Cloneable {
	
	/**
	 *  Creates an exact copy of the object (new instance)
	 * @return
	 */
	public Object clone();
}

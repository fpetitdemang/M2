
public interface Visitor {
	//declare autant de methode que de type d'element
	public void visitDirectory(Directory m_directory);
	public void visitFile(File m_file);
	
	//Probleme : avec le type de retour dans les implementations de l'interface
	//Solution : utilisation genericité??
}

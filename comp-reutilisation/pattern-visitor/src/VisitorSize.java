//VisitorOperation
public class VisitorSize implements Visitor {
	
	//private int taille = 0;


	@Override
	public void visitDirectory(Directory m_directory) {
		//ici, la taille d'un dossier est gale  la somme des tailles des diffrents lments prsents dans ce dossier.
		
		/*int somme= m_directory.basicSize ;
				
		for (ElementStockage s : m_directory.listeDossier) // parcours de la collection (parcours de tout le dossier)
		{
			somme=somme+s.size() ;
		}
				
		 System.out.println(somme);*/
	}

	@Override
	public void visitFile(File m_file) {
		System.out.println(m_file.getContenu().length());
		// TODO Auto-generated method stub
		
	}

	
}

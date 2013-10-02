//VisitorOperation
public class VisitorSize implements Visitor {
	
	@Override
	public void visitArchive(Archive m_archive) {
		//return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitDirectory(Directory m_directory) {
		//ici, la taille d'un dossier est gale  la somme des tailles des diffrents lments prsents dans ce dossier.
		
		int somme= m_directory.basicSize ;
				
		for (ElementStockage s : m_directory.listeDossier) // parcours de la collection (parcours de tout le dossier)
		{
			somme=somme+s.size() ;
		}
				
		//return somme;
	}

	@Override
	public void visitFile(File m_file) {
		//return 0;
		// TODO Auto-generated method stub
		
	}

	
}


public class VisitorLs implements Visitor{


	@Override
	public void visitDirectory(Directory m_directory) {
		for (ElementStockage s : m_directory.listeDossier)
		{
			System.out.println(s.name) ;
		}
		
	}

	@Override
	public void visitFile(File m_file) {
		System.out.println(m_file.getName());
		
	}

}

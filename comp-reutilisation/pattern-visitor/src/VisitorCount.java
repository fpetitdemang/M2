
public class VisitorCount implements Visitor{
	
	int count = 0;

	@Override
	public void visitDirectory(Directory m_directory) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void visitFile(File m_file) {
		if (m_file.getContenu().length() > 10) count++;		
	}
	
	public int getCount(){ return count;}

}

import java.util.ArrayList;
import java.util.List;

public class FilesCollDocument {
	private String fileTitle;
	private String fileType;
	private String fileDate;
	private String fileDetail;
	private String fileTags;
	private String fileOwnerID;
	private String fileLink;
	private boolean fileShareFlag;
	private int fileStaticsViews;
	private int fileStaticsFavorites;
	private int fileStaticsDownloads;
	private int fileCommentsNum;
	private List<String> fileCommentsList;

	public FilesCollDocument(){
		fileCommentsList = new ArrayList<String>();
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getFileDetail() {
		return fileDetail;
	}

	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

	public String getFileTags() {
		return fileTags;
	}

	public void setFileTags(String fileTags) {
		this.fileTags = fileTags;
	}

	public String getFileOwnerID() {
		return fileOwnerID;
	}

	public void setFileOwnerID(String fileOwnerID) {
		this.fileOwnerID = fileOwnerID;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public boolean isFileShareFlag() {
		return fileShareFlag;
	}

	public void setFileShareFlag(boolean fileShareFlag) {
		this.fileShareFlag = fileShareFlag;
	}

	public int getFileStaticsViews() {
		return fileStaticsViews;
	}

	public void setFileStaticsViews(int fileStaticsViews) {
		this.fileStaticsViews = fileStaticsViews;
	}

	public int getFileStaticsFavorites() {
		return fileStaticsFavorites;
	}

	public void setFileStaticsFavorites(int fileStaticsFavorites) {
		this.fileStaticsFavorites = fileStaticsFavorites;
	}

	public int getFileStaticsDownloads() {
		return fileStaticsDownloads;
	}

	public void setFileStaticsDownloads(int fileStaticsDownloads) {
		this.fileStaticsDownloads = fileStaticsDownloads;
	}

	public int getFileCommentsNum() {
		return fileCommentsNum;
	}

	public void setFileCommentsNum(int fileCommentsNum) {
		this.fileCommentsNum = fileCommentsNum;
	}

	public List<String> getFileCommentsList() {
		return fileCommentsList;
	}

	public void setFileCommentsList(List<String> fileCommentsList) {
		this.fileCommentsList = fileCommentsList;
	}
}

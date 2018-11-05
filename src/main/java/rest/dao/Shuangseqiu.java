package rest.dao;

import java.util.List;

public class Shuangseqiu {

	private final String blue;
	private final List<Integer> reds;

	public String getBlue() {
		return blue;
	}

	public List<Integer> getReds() {
		return reds;
	}

	public Shuangseqiu(String blue, List<Integer> reds) {
		this.blue = blue;
		this.reds = reds;
	}

}

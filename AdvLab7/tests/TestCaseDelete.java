package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestCaseDelete {
	static music.PlayList p;

	@Test
	public void testDelShowMulti() {
		p = new music.PlayList("PlayList 2");
		music.Song a = new music.Song("Song1","Singer1",200);
		p.add(a);
		a = new music.Song("Song 2", "Singer 2", 10);
		p.add(a);
		a = new music.Song("Oh no","What a Singer",100);
		p.add(a);
		
		String y = p.show();
		assertEquals(y,"Name: Song1\nSinger: Singer1\nDuration: 200 s\n\nName: Song 2\nSinger: Singer 2\nDuration: 10 s\n\nName: Oh no\nSinger: What a Singer\nDuration: 100 s\n\n");
		p.remove("Song 2");
		String z = p.show();
		assertEquals(z,"Name: Song1\nSinger: Singer1\nDuration: 200 s\n\nName: Oh no\nSinger: What a Singer\nDuration: 100 s\n\n");
	}
}

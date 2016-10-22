/**
 * A special line object with it's special tag symbol. Can be used for horizontal bars.
 *
 * @author Ofek Gila
 * @author Saagar Jha
 * @since October 20th, 2016
 */

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;

public class SpecialLine extends Line {

	private String tag;

	/**
	 * SpecialLine constructor with all the necessary values
	 * @param  tag       the tag corresponding to the symbol to display
	 * @param  font      the {@link Font} to use
	 * @param  color     the {@link Color} to use
	 * @param  component any old {@link JComponent} to get font metrics from
	 */
	public SpecialLine(String tag, Font font, Color color, JComponent component) {
		super("", font, color, component);
		this.tag = tag;
	}

	/**
	 * Gets the tag for this special line
	 * @return [description]
	 */
	public String getTag() {
		return tag;
	}

	@Override
	public int getLineHeight() {
		switch (tag) {
			case "hr":
				return 8;
			default:
				return 0;
		}
	}

	@Override
	public int getLineWidth() {
		switch (tag) {
			case "hr":
				return -1;
			default:
				return super.getLineWidth();
		}
	}
}

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * The main class for SimpleHtmlRenderer, a program that can help pretty-print
 * tokenized HTML. {@link SimpleHtmlRenderer} supports various colors, fonts, and
 * even some special tags such as horizontal rules. A
 * {@link JFrame}, it manages a single {@link HtmlCanvas} and allows
 * printing to it through a {@link HtmlPrinter}.
 *
 * @author Ofek Gila
 * @author Saagar Jha
 * @since October 20th, 2016
 */
public class SimpleHtmlRenderer extends JFrame {

	private static final int DEFAULT_WINDOW_WIDTH = 1000;
	private static final int DEFAULT_WINDOW_HEIGHT = 750;

	private final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	private int windowWidth, windowHeight;
	private HtmlCanvas htmlCanvas;
	private JScrollPane scrollPane;
	private HtmlPrinter htmlPrinter;

	/**
	 * Main constructor settings width and height.
	 * @param  windowWidth  width of the frame
	 * @param  windowHeight height of the frame
	 */
	public SimpleHtmlRenderer(int windowWidth, int windowHeight) {
		super("Simple Browser");
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;

		setSize(windowWidth, windowHeight);
		center();
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setLayout(new BorderLayout());
		createWindow();
		htmlPrinter = new HtmlPrinter(this, htmlCanvas);
		htmlCanvas.setHtmlComponents(htmlPrinter.getHtmlComponents());
		addScrollPane();
	}

	/**
	 * Constructor without width and height (uses defaults).
	 */
	public SimpleHtmlRenderer() {
		this(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
	}

	/**
	 * Centers frame on screen.
	 */
	private void center() {
		setLocation((SCREEN_WIDTH - windowWidth) / 2, (SCREEN_HEIGHT - windowHeight) / 2);
	}

	/**
	 * Sets the {@link HtmlCanvas} to new appropriate width and height given its htmlComponents, and refreshes the {@link JScrollPane}.
	 */
	public void cleanupAfterPrint() {
		htmlCanvas.setPreferredSize(new Dimension(htmlCanvas.getWidth(), htmlCanvas.getHeight()));
		revalidate();
		scrollPane.revalidate();
	}

	/**
	 * Creates the {@link HtmlCanvas} for this class to fill whole screen except scrollbars.
	 */
	private void createWindow() {
		htmlCanvas = new HtmlCanvas();
		htmlCanvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
		add(htmlCanvas, BorderLayout.CENTER);
	}

	/**
	 * Returns the {@link HtmlPrinter} object for the {@link HtmlCanvas}.
	 * @return the {@link HtmlPrinter} object
	 */
	public HtmlPrinter getHtmlPrinter() {
		return htmlPrinter;
	}

	/**
	 * Adds a {@link JScrollPane} to this frame, and sets its default increment for scrolling.
	 */
	private void addScrollPane() {
		scrollPane = new JScrollPane(htmlCanvas);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
		add(scrollPane);
		scrollPane.requestFocus();
	}

	public static void main(String... pumpkins) {
		SimpleHtmlRenderer simpleHtmlPrinter = new SimpleHtmlRenderer();
		HtmlPrinter htmlPrinter = simpleHtmlPrinter.getHtmlPrinter();

		// An example, for testing purposes
		htmlPrinter.printPreformattedText("This text is pre-formatted!");
		htmlPrinter.println(); // this break is necessary as the fonts are the same size (try removing it)
		htmlPrinter.printPreformattedText("Words     line   up       !");
		htmlPrinter.println();

		htmlPrinter.print("normal ");
		htmlPrinter.printItalic("italic ");
		htmlPrinter.printBold("bold");
		htmlPrinter.printBreak();

		htmlPrinter.setFont(new Font("SansSerif", Font.ITALIC, 22));
		htmlPrinter.print("Leaning Tower of ");
		htmlPrinter.printBold("Pisa");

		htmlPrinter.setFont(new Font("Serif", Font.PLAIN, 18));
		htmlPrinter.printParagraph("Hello World");
		htmlPrinter.setFont(new Font("SansSerif", Font.ITALIC, 50));
		htmlPrinter.printParagraph("Other World");

		htmlPrinter.printHorizontalRule();

		htmlPrinter.setFont(new Font("Serif", Font.PLAIN, 22));
		htmlPrinter.printParagraph("Some normal text is much needed over here");

		htmlPrinter.printBreak();
		htmlPrinter.printBreak();
		htmlPrinter.printParagraph("Time to take a break (or two)!");

		htmlPrinter.setFont(new Font("Times New Roman", Font.BOLD, 250));
		htmlPrinter.printParagraph("HUGE TEXT :D");

		htmlPrinter.printHeading1("H1 And now for something completely different");
		htmlPrinter.println(); // this println call is redundant
		// since heading 3 font is a different size than heading 1, it would
		// automatically println before outputting.
		htmlPrinter.printHeading3("Colors!!!");
		htmlPrinter.setFont(new Font("Arial", Font.PLAIN, 20));
		htmlPrinter.printBreak();

		htmlPrinter.setColor(Color.BLUE);
		htmlPrinter.setFont(new Font("Arial", Font.PLAIN, 22));

		htmlPrinter.printParagraph("This should be ");
		htmlPrinter.printBold("blue");
		htmlPrinter.print(" now XD");

		htmlPrinter.setColor(Color.RED);
		htmlPrinter.printParagraph(" And now red");

		htmlPrinter.printHorizontalRule();

		htmlPrinter.setFont(HtmlPrinter.DEFAULT_FONT);
		htmlPrinter.setColor(Color.GREEN);
		htmlPrinter.printParagraph("I'm glad this works!");
	}
}
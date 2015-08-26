package org.verapdf.model.impl.pb.pd.font;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.verapdf.model.impl.pb.pd.PBoxPDContentStream;
import org.verapdf.model.pdlayer.PDType3Font;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Timur Kamalov
 */
public class PBoxPDType3FontTest extends PBoxPDSimpleFontTest {

	private static final String TYPE3_FONT_NAME = "T3_0";
	private static final String TYPE3_SUBTYPE = "Type3";

	private static final Long WIDTHS_SIZE = 2l;
	private static final Long FIRST_CHAR = 97l;
	private static final Long LAST_CHAR = 98l;

	private static final long CONTENT_STREAMS_SIZE = 2l;

	@BeforeClass
	public static void setUp() throws IOException, URISyntaxException {
		expectedType = TYPES.contains(PBoxPDType3Font.TYPE3_FONT_TYPE) ? PBoxPDType3Font.TYPE3_FONT_TYPE : null;
		expectedID = null;

		setUp(FILE_RELATIVE_PATH);
		PDFont type3Font = document.getPage(0).getResources().getFont(COSName.getPDFName(TYPE3_FONT_NAME));
		actual = new PBoxPDType3Font(type3Font);
	}

	@Override
	public void testBaseFont() {
		Assert.assertNull(((PDType3Font) actual).getBaseFont());
	}

	@Override
	public void testSubtypeMethod() {
		Assert.assertEquals(TYPE3_SUBTYPE, ((PDType3Font) actual).getSubtype());
	}

	@Override
	public void testWidthsSize() {
		Assert.assertEquals(WIDTHS_SIZE, ((PDType3Font) actual).getWidths_size());
	}

	@Override
	public void testLastChar() {
		Assert.assertEquals(LAST_CHAR, ((PDType3Font) actual).getLastChar());
	}

	@Override
	public void testFirstChar() {
		Assert.assertEquals(FIRST_CHAR, ((PDType3Font) actual).getFirstChar());
	}

	@Override
	public void testIsStandard() {
		Assert.assertFalse(((PDType3Font) actual).getisStandard());
	}

	@Test
	public void testCharStrings() {
		List<? extends Object> contentStreams = ((PDType3Font) actual).getLinkedObjects(PBoxPDType3Font.CHAR_STRINGS);
		Assert.assertEquals(contentStreams.size(), CONTENT_STREAMS_SIZE);
		Assert.assertTrue(contentStreams.get(0) instanceof PBoxPDContentStream);
		Assert.assertTrue(contentStreams.get(1) instanceof PBoxPDContentStream);
	}
}
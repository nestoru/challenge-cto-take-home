package com.automwrite.assessment.service.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentService {

    /**
     * Saves the provided XWPFDocument to a specified file name.
     *
     * @param document The XWPFDocument to be saved
     * @throws IOException If an error occurs during the file writing process
     */
    public void saveDocument(XWPFDocument document) throws IOException {
        try (FileOutputStream out = new FileOutputStream("recommendation.docx")) {
            document.write(out);
        }
    }

    /**
     * Loads a predefined template from the resources folder.
     *
     * @return The loaded XWPFDocument template
     * @throws IOException If the template file is not found or cannot be loaded
     */
    public XWPFDocument loadTemplate() throws IOException {
        try (InputStream templateStream = getClass().getResourceAsStream("/templates/recommendation-template.docx")) {
            if (templateStream == null) {
                throw new IOException("Template file not found");
            }
            return new XWPFDocument(templateStream);
        }
    }

    /**
     * Inserts recommendation content into the template document.
     *
     * Locates the placeholder "// TODO INSERT ADVICE HERE" in the document
     * and replaces it with the generated recommendation content.
     *
     * @param document The XWPFDocument to modify
     * @param recommendationContent The recommendation text to insert
     * @throws IllegalStateException If no placeholder is found in the document
     */
    public void insertContentIntoTemplate(XWPFDocument document, String recommendationContent) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            if (text.contains("// TODO INSERT ADVICE HERE")) {
                // Clear existing paragraph
                paragraph.getCTP().newCursor();
                paragraph.setStyle("Normal");

                // Remove all existing runs
                while (paragraph.getRuns().size() > 0) {
                    paragraph.removeRun(0);
                }

                // Add recommendation content
                XWPFRun run = paragraph.createRun();
                run.setText(recommendationContent);
                return;
            }
        }

        // Throw an exception if placeholder is not found
        throw new IllegalStateException("Placeholder '// TODO INSERT ADVICE HERE' not found in the document");
    }
}

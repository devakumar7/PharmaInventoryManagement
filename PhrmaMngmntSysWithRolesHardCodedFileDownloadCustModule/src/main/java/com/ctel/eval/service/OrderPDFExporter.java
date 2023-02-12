package com.ctel.eval.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctel.eval.model.Customer;
import com.ctel.eval.model.Order;
import com.ctel.eval.model.Orderproduct;
import com.ctel.eval.model.Product;
import com.ctel.eval.model.Seller;
import com.ctel.eval.repository.CustomerRepo;
import com.ctel.eval.repository.ProductRepo;
import com.ctel.eval.repository.SellerRepo;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderPDFExporter {

	@Autowired
	private CustomerRepo custRepo;

	@Autowired
	private SellerRepo slrRepo;

	@Autowired
	private ProductRepo prodRepo;

	private Order ord;
	private List<Orderproduct> ordPdtList;
	private Customer cust;
	private Seller slr;
	private List<Product> pdtList;

	public OrderPDFExporter(Order ord, List<Orderproduct> ordPdtList, Customer cust, Seller slr,
			List<Product> pdtList) {
		super();
		this.ord = ord;
		this.ordPdtList = ordPdtList;
		this.cust = cust;
		this.slr = slr;
		this.pdtList = pdtList;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Customer Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Full Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Seller Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Seller Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Product Id", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Product Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Manufactured By", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Product Quantity", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Product Price", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Bill", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {

		System.out.println(ord.getCid());
		System.out.println(ord.getSellerId());
		System.out.println(ord.getBill());
		System.out.println(cust.toString());

		for (Orderproduct l : ordPdtList) {
			table.addCell(String.valueOf(ord.getCid()));
			table.addCell(cust.getEmailId());
			table.addCell(cust.getFirstName() + " " + cust.getLastName());

			table.addCell(String.valueOf(ord.getCreatedDate()));
			table.addCell(String.valueOf(ord.getSellerId()));
			table.addCell(String.valueOf(slr.getSellerName()));

			table.addCell(String.valueOf(l.getPid()));
			table.addCell(String.valueOf(l.getQty()));

			for (Product p : pdtList) {
				if (p.getPid() == l.getPid()) {
					table.addCell(p.getProdName());
					table.addCell(p.getManufactureName());
					table.addCell(String.valueOf(p.getPrice()));
				}
			}
		}
		table.addCell(String.valueOf(ord.getBill()));
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Order Invoice", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
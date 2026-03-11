package rentmycar.rentmycar.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentmycar.rentmycar.entity.Payment;


import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PaymentPdfService {

    public byte[] createPdf(Payment payment){

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter =  new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(pdfWriter);
        PageSize pageSize = PageSize.A4;
        Document document = new Document(pdf,pageSize);



        Paragraph paragraph = new Paragraph();
        paragraph.add("CONTRACT DE INCHIRIERE AUTO");
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setMarginBottom(15f);
        paragraph.setFontColor(new DeviceRgb(27, 90, 106));
        paragraph.setFontSize(18);
        document.add(paragraph);


        Table header = new Table(2).useAllAvailableWidth();
        header.setBorder(Border.NO_BORDER);

        Cell leftCell = new Cell().setBorder(Border.NO_BORDER);
        leftCell.setBorder(Border.NO_BORDER);
        leftCell.add(new Paragraph("Date: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        Cell rightCell = new Cell();
        rightCell.setBorder(Border.NO_BORDER);
        header.addCell(leftCell);
        header.addCell(rightCell);

        float[] mainColumn = {48f, 4f, 48f};

        Table firstLayout = new Table(UnitValue.createPercentArray(mainColumn)).useAllAvailableWidth();
        firstLayout.setBorder(Border.NO_BORDER);
        firstLayout.setMarginTop(10f);

        Cell leftPart = new Cell();
        leftPart.setBorder(Border.NO_BORDER);
        leftPart.add(new Paragraph("Compania de inchiriere:")
                .setBackgroundColor(new DeviceRgb(196, 243, 255)).setPaddingLeft(10f));
        leftPart.add(new Paragraph("Name: " + payment.getCompany().getCompanyName()));
        leftPart.add(new Paragraph("Address: " + payment.getCompany().getAddress()));
        leftPart.add(new Paragraph("Phone: "+ payment.getCompany().getTell()));
        leftPart.add(new Paragraph("Email: " + payment.getCompany().getEmail()));
        leftPart.add(new Paragraph("CUI: " + payment.getCompany().getCui()));

        Cell rightPart = new Cell();
        rightPart.setBorder(Border.NO_BORDER);
        rightPart.add(new Paragraph("Client: ").setBackgroundColor(new DeviceRgb(196, 243, 255)).setPaddingLeft(10f));
        rightPart.add(new Paragraph("Name: "
                +payment.getInvoice().getBooking().getCustomer().getLastName()+" "
                +payment.getInvoice().getBooking().getCustomer().getFirstName()));
        rightPart.add(new Paragraph("Email: " + payment.getInvoice().getBooking().getCustomer().getEmail()));
        rightPart.add(new Paragraph("Phone: "+ payment.getInvoice().getBooking().getCustomer().getPhone()));
        rightPart.add(new Paragraph("DriverLicenceNr: "+ payment.getInvoice().getBooking().getCustomer().getDriverLicenseNumber()));
        rightPart.add(new Paragraph("Data: "+ payment.getInvoice().getBooking().getCustomer().getCreatedAt()));

        Cell middlePart = new Cell();
        middlePart.setBorder(Border.NO_BORDER);

        firstLayout.addCell(leftPart);
        firstLayout.addCell(middlePart);
        firstLayout.addCell(rightPart);



        document.add(header);
        document.add(createLine());
        document.add(new Paragraph("1. Partile Contractate")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE));
        document.add(createLine());

        document.add(firstLayout);

        document.close();
        return out.toByteArray();
    }

    public Table createLine(){
        Table line = new Table(1).useAllAvailableWidth().setBorder(Border.NO_BORDER);
        Cell hr =  new Cell();
        hr.setBorder(Border.NO_BORDER);
        hr.setBorderBottom(new SolidBorder(ColorConstants.GRAY,0.1f));

        return line.addCell(hr);
    }


}

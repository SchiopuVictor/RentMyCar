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
        PageSize pageSize = new PageSize(550,600);
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
        leftCell.add(new Paragraph("Nr.chitanta: "+payment.getInvoice().getInvoiceNumber()).setFontColor(new DeviceRgb(27, 90, 106)));

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
                .setBackgroundColor(new DeviceRgb(196, 243, 255))
                .setPaddingLeft(5f)
                .setFontColor(new DeviceRgb(27, 90, 106))
                .setBold());
        leftPart.setFontColor(new DeviceRgb(27, 90, 106));
        leftPart.add(new Paragraph("Name: " + payment.getCompany().getCompanyName()));
        leftPart.add(new Paragraph("Address: " + payment.getCompany().getAddress()));
        leftPart.add(new Paragraph("Phone: "+ payment.getCompany().getTell()));
        leftPart.add(new Paragraph("Email: " + payment.getCompany().getEmail()));
        leftPart.add(new Paragraph("CUI: " + payment.getCompany().getCui()));

        Cell rightPart = new Cell();
        rightPart.setBorder(Border.NO_BORDER);
        rightPart.setFontColor(new DeviceRgb(27, 90, 106));
        rightPart.add(new Paragraph("Client: ")
                .setBackgroundColor(new DeviceRgb(196, 243, 255))
                .setPaddingLeft(5f)
                .setBold());
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

        Table secondLayout = new Table(UnitValue.createPercentArray(mainColumn)).useAllAvailableWidth().setMarginTop(3f);

        Cell leftMidPart = new Cell();
        leftMidPart.setBorder(Border.NO_BORDER);
        leftMidPart.setFontColor(new DeviceRgb(27, 90, 106));
        leftMidPart.add(new Paragraph("Model: " + payment.getInvoice().getBooking().getCar().getModel()));
        leftMidPart.add(new Paragraph("Brand: "+ payment.getInvoice().getBooking().getCar().getBrand()));
        leftMidPart.add(new Paragraph("Nr.Auto: "+payment.getInvoice().getBooking().getCar().getLicensePlate()));


        Cell rightMidPart = new Cell();
        rightMidPart.setFontColor(new DeviceRgb(27, 90, 106));
        rightMidPart.setBorder(Border.NO_BORDER);
        rightMidPart.add(new Paragraph("Year: "+payment.getInvoice().getBooking().getCar().getYear()));
        rightMidPart.add(new Paragraph("Date Expire: "+payment.getInvoice().getBooking().getCar().getCreatedAt()));

        secondLayout.addCell(leftMidPart);
        secondLayout.addCell(middlePart);
        secondLayout.addCell(rightMidPart);

        Table thirdLayout = new Table(UnitValue.createPercentArray(mainColumn)).useAllAvailableWidth().setMarginTop(3f);
        thirdLayout.setBorder(Border.NO_BORDER);

        Cell leftThirdPart = new Cell();
        leftThirdPart.setFontColor(new DeviceRgb(27, 90, 106));
        leftThirdPart.setBorder(Border.NO_BORDER);
        leftThirdPart.add(new Paragraph("Data preluari: "+ payment.getInvoice().getBooking().getStartDate()));
        leftThirdPart.add(new Paragraph("Nr.Zile: "+payment.getInvoice().getBooking().getTotalDays()));


        Cell rightThirdPart = new Cell();
        rightThirdPart.setFontColor(new DeviceRgb(27, 90, 106));
        rightThirdPart.setBorder(Border.NO_BORDER);
        rightThirdPart.add(new Paragraph("Data returnari: "+ payment.getInvoice().getBooking().getEndDate()));
        rightThirdPart.add(new Paragraph("Pretul pe zi: "+payment.getInvoice().getBooking().getPricePerDay()+" lei"));

        thirdLayout.addCell(leftThirdPart);
        thirdLayout.addCell(middlePart);
        thirdLayout.addCell(rightThirdPart);

        Table fourthLayout = new Table(UnitValue.createPercentArray(mainColumn)).useAllAvailableWidth().setMarginTop(3f);

        Cell leftFourthPart = new Cell();
        leftFourthPart.setFontColor(new DeviceRgb(27, 90, 106));
        leftFourthPart.setBorder(Border.NO_BORDER);
        leftFourthPart.add(new Paragraph("Trif inchirier: "+payment.getInvoice().getBooking().getPricePerDay()+" lei"));
        leftFourthPart.add(new Paragraph("Metoda de plata: "+ payment.getPaymentMethod()));

        Cell rightFourthPart = new Cell();
        rightFourthPart.setFontColor(new DeviceRgb(27, 90, 106));
        rightFourthPart.setBorder(Border.NO_BORDER);
        rightFourthPart.add(new Paragraph("Suma spre achitare: "+payment.getInvoice().getAmount()+" lei"));
        rightFourthPart.add(new Paragraph("Emisa la: "+payment.getInvoice().getIssuedAt()));

        fourthLayout.addCell(leftFourthPart);
        fourthLayout.addCell(middlePart);
        fourthLayout.addCell(rightFourthPart);

        Table footerLayout = new  Table(UnitValue.createPercentArray(mainColumn)).useAllAvailableWidth().setMarginTop(22f);
        footerLayout.setBorder(Border.NO_BORDER);

        Cell leftFooterPart = new Cell();
        leftFooterPart.setFontColor(new DeviceRgb(27, 90, 106));
        leftFooterPart.setBorder(Border.NO_BORDER);
        leftFooterPart.add(new Paragraph("Data: "+payment.getPaidAt()));

        Cell rightFooterPart = new Cell();
        rightFooterPart.setFontColor(new DeviceRgb(27, 90, 106));
        rightFooterPart.setBorder(Border.NO_BORDER);
        rightFooterPart.add(new Paragraph("Semnatura: "+ payment.getInvoice().getBooking().getCustomer().getLastName()));
        rightFooterPart.setTextAlignment(TextAlignment.RIGHT);

        footerLayout.addCell(leftFooterPart);
        footerLayout.addCell(middlePart);
        footerLayout.addCell(rightFooterPart);

        document.add(header);
        document.add(createLine());
        document.add(new Paragraph("1. Partile Contractate")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBold()
                .setFontColor(new  DeviceRgb(27, 90, 106)));
        document.add(createLine());
        document.add(firstLayout);
        document.add(createLine());
        document.add(new Paragraph("2. Detali despre vehicul")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBold()
                .setFontColor(new  DeviceRgb(27, 90, 106)));
        document.add(createLine());
        document.add(secondLayout);
        document.add(createLine());
        document.add(new Paragraph("3. Perioada inchirieri")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBold()
                .setFontColor(new  DeviceRgb(27, 90, 106)));
        document.add(createLine());
        document.add(thirdLayout);
        document.add(createLine());
        document.add(new Paragraph("4. Trife si conditi de plata")
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBold()
                .setFontColor(new  DeviceRgb(27, 90, 106)));
        document.add(createLine());
        document.add(fourthLayout);
        document.add(footerLayout);

        document.close();
        return out.toByteArray();
    }

    public Table createLine(){
        Table line = new Table(1).useAllAvailableWidth().setBorder(Border.NO_BORDER);
        Cell hr =  new Cell();
        hr.setBorder(Border.NO_BORDER);
        DeviceRgb rgb = new DeviceRgb(27, 90, 106);
        hr.setBorderBottom(new SolidBorder(rgb,0.1f));

        return line.addCell(hr);
    }


}

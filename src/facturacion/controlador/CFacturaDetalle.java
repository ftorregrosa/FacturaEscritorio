/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controlador;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import facturacion.datos.Cliente;
import facturacion.datos.Factura;
import facturacion.datos.Factura_;
import facturacion.servicio.Context;
import facturacion.servicio.Validaciones;
import facturacion.utils.Constantes;
import facturacion.utils.Cadenas;
import facturacion.vista.VPrincipal;
import facturacion.vista.VFactura;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;

/**
 *
 * @author JORDY
 */
public class CFacturaDetalle implements KeyListener, MouseListener, ActionListener {


    VFactura vfacturaDetalle;
    Context contexto;
    VPrincipal vprincipal;
    Factura_ factura;
    Cliente cliente;
    Validaciones validar = new Validaciones();

    DecimalFormat df = Cadenas.formatoDecimal();



    int numeroPantalla;


    DefaultTableModel modeloTablaFacturaProdutos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            return false;
        }
    };

    DefaultTableModel modeloTablaFactura = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3 || columnas == 5) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void fireTableCellUpdated(int row, int column) {
            super.fireTableCellUpdated(row, column);
            calculaFila(row);
            calculaTotales();
        }

    };

    TableRowSorter trs;
    TableRowSorter trs1;

    public CFacturaDetalle(Context context, VFactura vfacturaDetalle, VPrincipal vmain, Factura_ factura, Cliente cliente) {
        this.vfacturaDetalle = vfacturaDetalle;
        this.contexto = context;
        this.vprincipal = vmain;
        this.factura = factura;
        this.cliente = cliente;

        this.vfacturaDetalle.getBtnImprimir().addActionListener(this);
        this.vfacturaDetalle.getBtnCalcularFactura().addActionListener(this);
        this.vfacturaDetalle.getBtnActualizarProductos().addActionListener(this);
        this.vfacturaDetalle.getBtnEditarTabla().addActionListener(this);
        this.vfacturaDetalle.getTxtBuscarProductosFactura().addKeyListener(this);
        this.vfacturaDetalle.getTxtBuscarCodigo().addKeyListener(this);
        this.vfacturaDetalle.getTablaProductosFactura().addMouseListener(this);
        this.vfacturaDetalle.getTablaFactura().addMouseListener(this);

        this.vfacturaDetalle.getTxtNombreCliente().setText(cliente.getRazonSocial());
        this.vfacturaDetalle.getTxtCedulaCliente().setText(cliente.getCif());
        this.vfacturaDetalle.getTxtDireccionCliente().setText(cliente.getDireccion());
        this.vfacturaDetalle.getTxtTelefonoCliente().setText(cliente.getTelefono());
        this.vfacturaDetalle.getTxtCorreoCliente().setText(cliente.getPoblacion());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.vfacturaDetalle.getTxtFechaFactura().setText(dateFormat.format(factura.getFecha()));


        modeloTablaFacturaProdutos.addColumn("Código");
        modeloTablaFacturaProdutos.addColumn("Nombre");
        modeloTablaFacturaProdutos.addColumn("Precio");
        modeloTablaFacturaProdutos.addColumn("IVA");

        vfacturaDetalle.getTablaProductosFactura().setModel(modeloTablaFacturaProdutos);

        modeloTablaFactura.addColumn("Codigo");
        modeloTablaFactura.addColumn("Nombre");
        modeloTablaFactura.addColumn("Cantidad");
        modeloTablaFactura.addColumn("Precio");
        modeloTablaFactura.addColumn("Descuento");
        modeloTablaFactura.addColumn("Importe");
        modeloTablaFactura.addColumn("IVA");
        modeloTablaFactura.addColumn("Importe");
        modeloTablaFactura.addColumn("Total Línea");
        vfacturaDetalle.getTablaFactura().setModel(modeloTablaFactura);

    }

    public void validarCampos() {
//        validar.validarNumeros(vfacturaDetalle.getTxtCedulaClienteIngreso());
//        validar.limitarCaracteres(vfacturaDetalle.getTxtCedulaClienteIngreso(), 13);
    }

    public void iniciarVentana() {
        vfacturaDetalle.show();
        validarCampos();
        vfacturaDetalle.getTablaProductosFactura().getColumnModel().getColumn(Constantes.COL_FACT_PROD_CODIGO).setPreferredWidth(20);
        vfacturaDetalle.getTablaProductosFactura().getColumnModel().getColumn(Constantes.COL_FACT_PROD_NOMBRE).setPreferredWidth(350);
        vfacturaDetalle.getTablaProductosFactura().getColumnModel().getColumn(Constantes.COL_FACT_PROD_PRECIO).setPreferredWidth(33);
        vfacturaDetalle.getTablaProductosFactura().getColumnModel().getColumn(Constantes.COL_FACT_PROD_IVA).setPreferredWidth(33);

        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_CODIGO).setPreferredWidth(20);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_NOMBRE).setPreferredWidth(300);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_CANTIDAD).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_PRECIO_BASE).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_DESCUENTO).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_DESCUENTO_IMPORTE).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_IVA).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_IVA_IMPORTE).setPreferredWidth(40);
        vfacturaDetalle.getTablaFactura().getColumnModel().getColumn(Constantes.COL_FACT_TOTAL_LINEA).setPreferredWidth(40);

        calculaTotales();
        inicializaVariables();
        contexto.getProductoServicio().rellenaComboProductosFactura(modeloTablaFacturaProdutos);
        vfacturaDetalle.getTxtBuscarProductosFactura().setDocument(new Validaciones());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(vfacturaDetalle.getRbEfectivo());
        grupo.add(vfacturaDetalle.getRbTarjeta());

    }

    public void cleanTable() {
        for (int i = 0; i < vfacturaDetalle.getTablaProductosFactura().getRowCount(); i++) {
            modeloTablaFacturaProdutos.removeRow(i);
            i -= 1;
        }
    }

    public void limpiarCampos() {
        // vfacturaDetalle.getTxtCedulaClienteIngreso().setText("");
    }

    public void calculaFila() {
    }

    public void calculaTotales() throws ParseException {
        double totalProductos1 = 0;
        double iva1Total = 0;
        double iva2Total = 0;
        double iva3Total = 0;
        for (int j = 0; j < vfacturaDetalle.getTablaFactura().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + df.parse((String) vfacturaDetalle.getTablaFactura().getValueAt(j, 8)).floatValue();;
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;

            //Sumas Totales de IVA
            //TIPO DE IVA
            Integer tipoIVA = Integer.parseInt((String) vfacturaDetalle.getTablaFactura().getValueAt(j, 6));
            Double precioIva = df.parse((String) vfacturaDetalle.getTablaFactura().getValueAt(j, Constantes.COL_FACT_IVA_IMPORTE)).doubleValue();
            switch (tipoIVA) {
                case 4:
                    iva1Total = iva1Total + precioIva;
                    break;
                case 10:
                    iva2Total = iva2Total + precioIva;
                    break;
                case 21:
                    iva3Total = iva3Total + precioIva;
                    break;
            };
        }

        vfacturaDetalle.getTxtTotalIva1().setText(df.format(iva1Total));
        vfacturaDetalle.getTxtTotalIva2().setText(df.format(iva2Total));
        vfacturaDetalle.getTxtTotalIva3().setText(df.format(iva3Total));
        vfacturaDetalle.getTxtTotalFactura().setText(df.format(totalProductos1));
    }

    void imprimirFactura() {

        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);

        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 80);
        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextWrap(1, 2, 30, 80, "FACTURA DE VENTA");
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + 1);
        printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + 2);
        printer.printTextWrap(2, 3, 60, 80, "Hora: 12:22:51");
        printer.printTextWrap(3, 3, 1, 80, "Vendedor.  : " + 3 + " - " + 4);
        printer.printTextWrap(4, 4, 1, 80, "CLIENTE: " + 5);
        printer.printTextWrap(5, 5, 1, 80, "RUC/CI.: " + 6);
        printer.printTextWrap(6, 6, 1, 80, "DIRECCION: " + "");
        printer.printCharAtCol(7, 1, 80, "=");
        printer.printTextWrap(7, 8, 1, 80, "Codigo          Descripcion                Cant.      P  P.Unit.      P.Total");
        printer.printCharAtCol(9, 1, 80, "-");
        int filas = vfacturaDetalle.getTablaFactura().getRowCount();

        for (int i = 0; i < filas - 4; i++) {
            printer.printTextWrap(9 + i, 10, 1, 80, vfacturaDetalle.getTablaFactura().getValueAt(i, 0).toString() + "|" + vfacturaDetalle.getTablaFactura().getValueAt(i, 1).toString() + "| " + vfacturaDetalle.getTablaFactura().getValueAt(i, 2).toString() + "| " + vfacturaDetalle.getTablaFactura().getValueAt(i, 3).toString() + "|" + vfacturaDetalle.getTablaFactura().getValueAt(i, 4).toString());
        }

        if (filas > 15) {
            printer.printCharAtCol(filas + 1, 1, 80, "=");
            printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + vfacturaDetalle.getTxtTotalFactura().getText());
            printer.printCharAtCol(filas + 2, 1, 80, "=");
            printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
        } else {
            printer.printCharAtCol(25, 1, 80, "=");
            printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + vfacturaDetalle.getTxtTotalFactura().getText());
            printer.printCharAtCol(27, 1, 80, "=");
            printer.printTextWrap(27, 28, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");

        }
        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(vprincipal, "No existen impresoras instaladas");
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public void guardarCompraVenta() {

    }

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vfacturaDetalle.getTxtBuscarProductosFactura() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vfacturaDetalle.getTxtBuscarProductosFactura().getText(),Constantes.COL_FACT_PROD_NOMBRE));
        }
        if (vfacturaDetalle.getTxtBuscarCodigo() == ae.getSource()) {
            trs1.setRowFilter(RowFilter.regexFilter("(?i)" + vfacturaDetalle.getTxtBuscarCodigo().getText(),Constantes.COL_FACT_PROD_CODIGO));
        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {

        if (vfacturaDetalle.getTxtBuscarProductosFactura() == ae.getSource()) {
            trs = new TableRowSorter(modeloTablaFacturaProdutos);
            vfacturaDetalle.getTablaProductosFactura().setRowSorter(trs);
        }

        if (vfacturaDetalle.getTxtBuscarCodigo() == ae.getSource()) {
            trs1 = new TableRowSorter(modeloTablaFacturaProdutos);
            vfacturaDetalle.getTablaProductosFactura().setRowSorter(trs1);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int tarjetasProductos(String codigo) {
        for (int j = 0; j < vfacturaDetalle.getTablaFactura().getRowCount(); j++) {
            String codigoEnRenglon = (String) vfacturaDetalle.getTablaFactura().getValueAt(j, 0);
            if (codigo.equals(codigoEnRenglon)) {
                return j;
            }
        }
        return -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vfacturaDetalle.getTablaProductosFactura() == e.getSource()) {
            try {
                int fila = vfacturaDetalle.getTablaProductosFactura().getSelectedRow();
                if (fila >= 0) {

                    int codProducto = Integer.valueOf((String) vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, 0));
                    String cantidad = JOptionPane.showInputDialog(vprincipal, "Introduzca la cantidad o kilos");
                    String descuento = JOptionPane.showInputDialog(vprincipal, "Introduzca el % de Descuento","0");

                    float cantidadProducto = Float.parseFloat(cantidad);
                    float descuentoProducto=Float.parseFloat(descuento);

                    Integer filaDuplicada = encontrarProductoFacturado((String) vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, Constantes.COL_FACT_PROD_CODIGO));
                    Double precio = Double.parseDouble((String) vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, Constantes.COL_FACT_PROD_PRECIO));
                    Integer iva = Integer.parseInt((String) vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, Constantes.COL_FACT_PROD_IVA));

                    if (filaDuplicada >= 0) {
                        String totalCantidad = vfacturaDetalle.getTablaFactura().getValueAt(filaDuplicada, Constantes.COL_FACT_CANTIDAD).toString();
                        float tc = Float.parseFloat(totalCantidad);
                        cantidadProducto = tc + cantidadProducto; 
                        modeloTablaFactura.removeRow(filaDuplicada);
                    } 
                    
                    

                    double totalProducto = cantidadProducto * precio;
                    double totalConDescuento=totalProducto-(totalProducto * descuentoProducto / 100);
                    totalProducto = Math.round(totalProducto * 100) / 100d;

                    String datosTabla[] = new String[9];
                    datosTabla[Constantes.COL_FACT_CODIGO] = vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, Constantes.COL_FACT_PROD_CODIGO).toString();//CODIGO
                    datosTabla[Constantes.COL_FACT_NOMBRE] = vfacturaDetalle.getTablaProductosFactura().getValueAt(fila, Constantes.COL_FACT_PROD_NOMBRE).toString();//NOMBRE
                    datosTabla[Constantes.COL_FACT_CANTIDAD] = cantidad;
                    datosTabla[Constantes.COL_FACT_PRECIO_BASE] = String.valueOf(precio);
                    datosTabla[Constantes.COL_FACT_DESCUENTO] = descuento;
                    datosTabla[Constantes.COL_FACT_DESCUENTO_IMPORTE] =df.format(totalProducto * descuentoProducto / 100);
                    datosTabla[Constantes.COL_FACT_IVA] = String.valueOf(iva);//IVA
                    datosTabla[Constantes.COL_FACT_IVA_IMPORTE] = df.format(totalConDescuento * iva / 100);
                    datosTabla[Constantes.COL_FACT_TOTAL_LINEA] = df.format(totalConDescuento+(totalConDescuento * iva / 100));
                    modeloTablaFactura.addRow(datosTabla);

                    vfacturaDetalle.getTablaProductosFactura().getSelectionModel().removeSelectionInterval(fila, fila);
                    vfacturaDetalle.getTablaFactura().scrollRectToVisible(vfacturaDetalle.getTablaFactura().getCellRect(vfacturaDetalle.getTablaFactura().getRowCount() - 1, 0, true));
                    
                    calculaTotales();

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
            }
        }
        if (e.getClickCount() == 2 && !e.isConsumed() && vfacturaDetalle.getTablaFactura() == e.getSource()) {
            try {
                int fila = vfacturaDetalle.getTablaFactura().getSelectedRow();
                if (fila >= 0) {
                    modeloTablaFactura.removeRow(fila);
                    calculaTotales();

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
            }
        }
    }

    public int encontrarProductoFacturado(String codigo) {
        for (int j = 0; j < vfacturaDetalle.getTablaFactura().getRowCount(); j++) {
            String codigoEnRenglon = (String) vfacturaDetalle.getTablaFactura().getValueAt(j, Constantes.COL_FACT_CODIGO);
            if (codigo.equals(codigoEnRenglon)) {
                return j;
            }
        }
        return -1;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int metodoPago() {
        int metodo = 1;
        if (vfacturaDetalle.getRbEfectivo().isSelected()) {
            metodo = 2;
            return metodo;
        }
        return metodo;
    }

    public void crearCarpetas1(String nombreProyecto) {
        File directorio = new File("A:\\FACTURACION FOREST BEEF\\" + nombreProyecto);
        directorio.mkdirs();
    }

    public void crearPDF() {
        String codigoPedido = vfacturaDetalle.getTxtNumeroFactura().getText();
        String nomProyecto = vfacturaDetalle.getTxtNombreCliente().getText();
        String direccion = vfacturaDetalle.getTxtDireccionCliente().getText();
        String telefono = vfacturaDetalle.getTxtCedulaCliente().getText();
        String fechaPedido = vfacturaDetalle.getTxtFechaFactura().getText();
        crearCarpetas1(fechaPedido);
        JFileChooser dlg = new JFileChooser("A:/FACTURACION FOREST BEEF/" + fechaPedido);
        dlg.setSelectedFile(new File("FAC_" + fechaPedido + "_" + codigoPedido));

        Font fuente1 = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
        Font fuente2 = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
        Font fuente3 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

        int opcion = dlg.showSaveDialog(vprincipal);
        //System.out.println(opcion);
        if (opcion == JFileChooser.APPROVE_OPTION) {

            File f = dlg.getSelectedFile();

            String ruta = dlg.getSelectedFile().toString().concat(".pdf");
            File f2 = new File(ruta);

            try {
                FileOutputStream archivo = new FileOutputStream(f + ".pdf");
                Rectangle pageSize = new Rectangle(270f, 568f); //ancho y alto
                Document doc = new Document(pageSize);
                doc.setMargins(22, 7, 10, 0); // (izq, der, arriba, abajo)
                //Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, archivo);
                doc.open();
                Paragraph Encabezado = new Paragraph();
                Paragraph FinalPag = new Paragraph();
                Paragraph producto = new Paragraph();
                Encabezado.setFont(fuente2);
                FinalPag.setFont(fuente2);

//                if (Integer.parseInt(codigoPedido) >= 1 && Integer.parseInt(codigoPedido) < 10) {
//                    numero.add("COTIZACION No. 00" + codigoPedido);
//
//                } else if (Integer.parseInt(codigoPedido) >= 10 && Integer.parseInt(codigoPedido) < 100) {
//                    numero.add("COTIZACION No. 0" + codigoPedido);
//
//                } else {
//                    numero.add("COTIZACION No. " + codigoPedido);
//                }
                //numero.setAlignment(Element.ALIGN_RIGHT);
                Encabezado.add("             " + nomProyecto + "\r");
                Encabezado.add("               " + telefono + "                                  " + fechaPedido + "\r");
                Encabezado.add("                 " + direccion + "\n");
                Encabezado.setAlignment(Element.ALIGN_RIGHT);
                //  Encabezado.add("CAJERO.  " + encarProyecto + "\n");
                Encabezado.setAlignment(Element.ALIGN_LEFT);
                FinalPag.setAlignment(Element.ALIGN_BASELINE);
                doc.add(Encabezado);
                doc.add(FinalPag);
                doc.add(new Paragraph("\n \n", fuente1));

                PdfPTable tablaPedido = new PdfPTable(3);
                // tablaPedido.getDefaultCell().setBorder(0);
                // tablaPedido.getDefaultCell().setBorderWidth(0f);
                tablaPedido.getDefaultCell().setBorderColor(BaseColor.YELLOW);
                // tablaPedido.DefaultCell.Border = 0;
                tablaPedido.setHorizontalAlignment(Element.ALIGN_LEFT);
                tablaPedido.setWidthPercentage(80f);
                tablaPedido.setWidths(new float[]{0.10f, 0.50f, 0.20f});
                //tablaPedido.getDefaultCell().setBorder(0);
                // tablaPedido.getDefaultCell().setBorderWidth(0f);
                tablaPedido.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//                PdfPCell cellOn = (new PdfPCell(new Phrase(" ", fuente1)));
//                PdfPCell cellO = (new PdfPCell(new Phrase("Cant", fuente1)));
//                PdfPCell cell = (new PdfPCell(new Phrase("Precio", fuente1)));
//                PdfPCell cel = (new PdfPCell(new Phrase("Total", fuente1)));
//                cellOn.setBorder(0);
//                cellO.setBorder(0);
//                cell.setBorder(0);
//                cel.setBorder(0);
//                
//    
//                tablaPedido.addCell(cellOn);
//                tablaPedido.addCell(cellO);
//                tablaPedido.addCell(cell);
//                tablaPedido.addCell(cel);
//                tablaPedido.addCell(new PdfPCell(new Phrase("Codigo", fuente1)));
//                tablaPedido.addCell(new PdfPCell(new Phrase("Productos", fuente1)));
//                tablaPedido.addCell(new PdfPCell(new Phrase("Cantidad", fuente1)));
//                tablaPedido.addCell(new PdfPCell(new Phrase("Precio", fuente1)));
//                tablaPedido.addCell(new PdfPCell(new Phrase("Total", fuente1)));
                for (int i = 0; i < vfacturaDetalle.getTablaFactura().getRowCount(); i++) {
                    String nomProducto = vfacturaDetalle.getTablaFactura().getValueAt(i, 2).toString();
                    String cantidad = vfacturaDetalle.getTablaFactura().getValueAt(i, 1).toString();
                    // String precio = vfacturaDetalle.getTablaFactura().getValueAt(i, 3).toString();
                    String total = vfacturaDetalle.getTablaFactura().getValueAt(i, 4).toString();

                    PdfPCell celdasProducto = (new PdfPCell(new Phrase(nomProducto, fuente2)));
                    PdfPCell celdasProduct = (new PdfPCell(new Phrase(cantidad, fuente2)));
                    // PdfPCell celdasProduc=(new PdfPCell(new Phrase(precio, fuente2)));
                    PdfPCell celdasProdu = (new PdfPCell(new Phrase(total, fuente3)));

                    celdasProducto.setBorder(0);
                    celdasProduct.setBorder(0);
                    // celdasProduc.setBorder(0);
                    celdasProdu.setBorder(0);

                    tablaPedido.addCell(celdasProducto);
                    tablaPedido.addCell(celdasProduct);
                    // tablaPedido.addCell(celdasProduc);
                    tablaPedido.addCell(celdasProdu);

                }
                doc.add(tablaPedido);

                FooterPiePaginaiText footer = new FooterPiePaginaiText();
                writer.setPageEvent(footer);

                doc.close();

                JOptionPane.showMessageDialog(vprincipal, "Archivo Guardado");
                Desktop.getDesktop().open(f2);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(vprincipal, e);
            }

        }
    }

    public void imprimir(File file) {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.vfacturaDetalle.getBtnImprimir() == ae.getSource()) {

            int resultadoMetodoPago = metodoPago();
            if (resultadoMetodoPago == 2) {
                try {
                    double superTotal = 0;
                    String entrega = JOptionPane.showInputDialog(vprincipal, "Introduzca el precio");
                    float entregaF = Float.parseFloat(entrega);
                    superTotal = (entregaF - (Float.parseFloat(vfacturaDetalle.getTxtTotalFactura().getText())));

                    superTotal = Math.round(superTotal * 100) / 100d;

                    JOptionPane.showMessageDialog(vprincipal, "VUELTO: " + superTotal);

                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(vprincipal, "No a ingresado bn el valor recibido");
                }
            }
            contextFacturaDetalle.RunAnadirFacturaDetalle(modeloTablaFactura, vfacturaDetalle.getTxtCedulaCliente().getText(), vfacturaDetalle.getTxtNumeroFactura().getText(), vprincipal.getLblCedula().getText(), resultadoMetodoPago);

            guardarCompraVenta();
            crearPDF();
            vfacturaDetalle.dispose();
        }

        if (this.vfacturaDetalle.getBtnCalcularFactura() == ae.getSource()) {

            calcularTotal();
        }

        if (this.vfacturaDetalle.getBtnEditarTabla() == ae.getSource()) {
            vfacturaDetalle.getBtnImprimir().setEnabled(false);
            quitarUltimasFilas();
        }

        if (this.vfacturaDetalle.getBtnActualizarProductos() == ae.getSource()) {
            cleanTable();
            contextFacturaDetalle.RunVisualizar(modeloTablaFacturaProdutos, 1);
        }

        if (this.vfacturaDetalle.getBtnAnular() == ae.getSource()) {
            String anulado = "SI";
            aumentarStock();
            contextFacturaDetalle.RunModificarContrasena(anulado, vfacturaDetalle.getTxtNumeroFactura().getText());
            int confirmado = JOptionPane.showConfirmDialog(vprincipal, "Crear una nueva factura");

            if (JOptionPane.OK_OPTION == confirmado) {
                vfacturaDetalle.getTablaFactura().setEnabled(true);
                vfacturaDetalle.getBtnCalcularFactura().setEnabled(true);
                vfacturaDetalle.getTablaProductosFactura().setEnabled(true);

                Factura factura = new Factura();
                factura.setFechaFactura(vfacturaDetalle.getTxtFechaFactura().getText());
                factura.setAnuladaFactura("NO");
                contextFacturaDetalle.RunAnadir(factura, 1);

                String codigoFactura = contextFacturaDetalle.RunCodigo();
                vfacturaDetalle.getTxtNumeroFactura().setText(codigoFactura);

                contextFacturaDetalle.RunVisualizarContra(vfacturaDetalle.getTxtCedulaCliente(), vfacturaDetalle.getTxtDireccionCliente(), vfacturaDetalle.getTxtTelefonoCliente(), vfacturaDetalle.getTxtCorreoCliente(), vfacturaDetalle.getTxtCedulaCliente().getText());
                vfacturaDetalle.getBtnAnular().setEnabled(false);
            } else {
                vfacturaDetalle.dispose();
            }
        }

    }

    private void inicializaVariables() {



    }
}

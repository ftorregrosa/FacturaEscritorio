/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import com.itextpdf.text.BaseColor;

import facturacioncarniceria.datos.Cotizacion;
import facturacioncarniceria.datos.CotizacionDetalle;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.estrategia.CotizacionDetalleEstrategia;
import facturacioncarniceria.modelo.Conexion;
import facturacioncarniceria.modelo.CotizacionDetalleDAO;
import facturacioncarniceria.modelo.Validaciones;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VCotizacion;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.io.FileOutputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

/**
 *
 * @author JORDY
 */
public class CCotizacionDetalle implements KeyListener, MouseListener, ActionListener {

    VCotizacion vcotizacionDetalle;
    Context contextFacturaDetalle;
    VPrincipal vprincipal;
    CotizacionDetalleDAO cotizacionDetalleDAO;
    CotizacionDetalleEstrategia strategyCotizacionDetalle = new CotizacionDetalleEstrategia();
    Conexion connectionBD;
    Validaciones validar = new Validaciones();

    int numeroPantalla;

    DefaultTableModel modeloTablaCotizacionProdutos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 3) {
                return true;
            } else {
                return false;
            }
        }
    };

    DefaultTableModel modeloTablaFactura = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            if (columnas == 5) {
                return true;
            } else {
                return false;
            }
        }
    };

    TableRowSorter trs;

    public void crearCarpetas1(String nombreProyecto) {
        File directorio = new File("c:\\COTIZACION FOREST BEEF\\" + nombreProyecto);
        directorio.mkdirs();
    }

    public void crearPDF() {
        String codigoPedido = vcotizacionDetalle.getTxtNumeroCotizacion().getText();
        String nomProyecto = vcotizacionDetalle.getTxtClienteCotizacion().getText();
        String encarProyecto = vprincipal.getLblNombreUsuario().getText();
        String fechaPedido = vcotizacionDetalle.getTxtFechaInicioCo().getText();
        crearCarpetas1(nomProyecto);
        JFileChooser dlg = new JFileChooser("c:/COTIZACION FOREST BEEF/");
        dlg.setSelectedFile(new File("CT_" + nomProyecto + "_" + codigoPedido));
        
        Font fuente = new Font(Font.FontFamily.COURIER, 14, Font.BOLD);
        Font numeracion = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
        Font fuente1 = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);
        Font fuente2 = new Font(Font.FontFamily.COURIER, 10);
        Font fuente3 = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

        int opcion = dlg.showSaveDialog(vprincipal);
        if (opcion == JFileChooser.APPROVE_OPTION) {

            File f = dlg.getSelectedFile();

            String ruta = dlg.getSelectedFile().toString().concat(".pdf");
            File f2 = new File(ruta);

            try {
                FileOutputStream archivo = new FileOutputStream(f + ".pdf");
                //Rectangle pageSize = new Rectangle(200f, 400f); //ancho y alto
                //Document doc = new Document(pageSize);
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo);
                doc.open();
               // Image imagen = Image.getInstance("costos.png");
               // imagen.setAlignment(Element.ALIGN_LEFT);
                
                Paragraph Encabezado = new Paragraph();
                Paragraph Titulo = new Paragraph();
                Paragraph producto = new Paragraph();
                Paragraph numero = new Paragraph();
                Titulo.setFont(fuente);
                Encabezado.setFont(fuente2);
                numero.setFont(numeracion);

                if (Integer.parseInt(codigoPedido) >= 1 && Integer.parseInt(codigoPedido) < 10) {
                    numero.add("COTIZACION No. 00" + codigoPedido);

                } else if (Integer.parseInt(codigoPedido) >= 10 && Integer.parseInt(codigoPedido) < 100) {
                    numero.add("COTIZACION No. 0" + codigoPedido);

                } else {
                    numero.add("COTIZACION No. " + codigoPedido);
                }

                numero.setAlignment(Element.ALIGN_RIGHT);
                Titulo.add("CLIENTE: " + nomProyecto + "\n");
                Titulo.setAlignment(Element.ALIGN_RIGHT);
                Encabezado.add("AUTORIZADO POR.  " + encarProyecto + "\n");
                Encabezado.add("FECHA COTIZACIÓN   " + fechaPedido);
                Encabezado.setAlignment(Element.ALIGN_LEFT);
                doc.add(numero);
                doc.add(Titulo);
                doc.add(Encabezado);
             //    doc.add(imagen);

                producto.setFont(fuente1);
                producto.add("\n PRODUCTOS \n\n");
                producto.setAlignment(Element.ALIGN_CENTER);
                doc.add(producto);
                PdfPTable tablaPedido = new PdfPTable(5);
               // tablaPedido.getDefaultCell().setBorder(0);
               // tablaPedido.getDefaultCell().setBorderWidth(0f);
                tablaPedido.getDefaultCell().setBorderColor(BaseColor.YELLOW);
               // tablaPedido.DefaultCell.Border = 0;
                tablaPedido.setHorizontalAlignment(Element.ALIGN_LEFT);
                tablaPedido.setWidthPercentage(100f);
                tablaPedido.setWidths(new float[]{0.10f, 0.60f, 0.15f, 0.10f, 0.10f});
                //tablaPedido.getDefaultCell().setBorder(0);
               // tablaPedido.getDefaultCell().setBorderWidth(0f);
                tablaPedido.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//               
//                PdfPCell cellOne = (new PdfPCell(new Phrase("Codigo", fuente1)));
//                PdfPCell cellOn = (new PdfPCell(new Phrase("Productos", fuente1)));
//                PdfPCell cellO = (new PdfPCell(new Phrase("Cantidad", fuente1)));
//                PdfPCell cell = (new PdfPCell(new Phrase("Precio", fuente1)));
//                PdfPCell cel = (new PdfPCell(new Phrase("Total", fuente1)));
//                cellOne.setBorder(0);
//                cellOn.setBorder(0);
//                cellO.setBorder(0);
//                cell.setBorder(0);
//                cel.setBorder(0);
//                
//                tablaPedido.addCell(cellOne);
//                tablaPedido.addCell(cellOn);
//                tablaPedido.addCell(cellO);
//                tablaPedido.addCell(cell);
//                tablaPedido.addCell(cel);
                
                
                tablaPedido.addCell(new PdfPCell(new Phrase("Codigo", fuente1)));
                tablaPedido.addCell(new PdfPCell(new Phrase("Productos", fuente1)));
                tablaPedido.addCell(new PdfPCell(new Phrase("Cantidad", fuente1)));
                tablaPedido.addCell(new PdfPCell(new Phrase("Precio", fuente1)));
                tablaPedido.addCell(new PdfPCell(new Phrase("Total", fuente1)));

                for (int i = 0; i < vcotizacionDetalle.getTablaCotizacion().getRowCount(); i++) {
                    String codigo = vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 0).toString();
                    String nomProducto = vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 1).toString();
                    String cantidad = vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 2).toString();
                    String precio = vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 3).toString();
                    String total = vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 4).toString();
//
//                    PdfPCell celdasProductos=(new PdfPCell(new Phrase(codigo, fuente2)));
//                    PdfPCell celdasProducto=(new PdfPCell(new Phrase(nomProducto, fuente2)));
//                    PdfPCell celdasProduct=(new PdfPCell(new Phrase(cantidad, fuente2)));
//                    PdfPCell celdasProduc=(new PdfPCell(new Phrase(precio, fuente2)));
//                    PdfPCell celdasProdu=(new PdfPCell(new Phrase(total, fuente3)));
//                    
//                celdasProductos.setBorder(0);
//                celdasProducto.setBorder(0);
//                celdasProduct.setBorder(0);
//                celdasProduc.setBorder(0);
//                celdasProdu.setBorder(0);
//                    
                    
//     
//                tablaPedido.addCell(celdasProductos);
//                tablaPedido.addCell(celdasProducto);
//                tablaPedido.addCell(celdasProduct);
//                tablaPedido.addCell(celdasProduc);
//                tablaPedido.addCell(celdasProdu);

                    tablaPedido.addCell(new PdfPCell(new Phrase(codigo, fuente2)));
                    tablaPedido.addCell(new PdfPCell(new Phrase(nomProducto, fuente2)));
                    tablaPedido.addCell(new PdfPCell(new Phrase(cantidad, fuente2)));
                    tablaPedido.addCell(new PdfPCell(new Phrase(precio, fuente2)));
                    tablaPedido.addCell(new PdfPCell(new Phrase(total, fuente3)));

                    
                }

                doc.add(tablaPedido);

                // doc.add(new Paragraph("                                                                                      "));
                doc.add(new Paragraph("\n \n _________________________", fuente1));
                doc.add(new Paragraph("AUTORIZADO POR: " + encarProyecto));
                doc.add(new Paragraph("\n OBSERVACIONES", fuente1));
                doc.add(new Paragraph("_____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________", fuente1));
                doc.close();

                JOptionPane.showMessageDialog(vprincipal, "Archivo Guardado");

                Desktop.getDesktop().open(f2);
                //vcotizacionHacer.getBtnEnviarCorreo().setEnabled(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vprincipal, e);
            }
        }
    }

    public CCotizacionDetalle(Context context, VCotizacion vcotizacionDetalle, VPrincipal vmain, int numero) {
        cotizacionDetalleDAO = new CotizacionDetalleDAO();
        this.numeroPantalla = numero;
        this.vcotizacionDetalle = vcotizacionDetalle;
        this.contextFacturaDetalle = context;
        this.vprincipal = vmain;
        this.contextFacturaDetalle = new Context(strategyCotizacionDetalle);

        this.vcotizacionDetalle.getBtnGenerarPDFCotizacion().addActionListener(this);
        this.vcotizacionDetalle.getBtnCalcularFactura().addActionListener(this);
        this.vcotizacionDetalle.getBtnEditarTabla().addActionListener(this);
        this.vcotizacionDetalle.getTxtBuscarProductosCotizacion().addKeyListener(this);
        this.vcotizacionDetalle.getTablaCotizacion().addMouseListener(this);
        this.vcotizacionDetalle.getTablaProductosCotizacion().addMouseListener(this);

        this.connectionBD = new Conexion();

        modeloTablaCotizacionProdutos.addColumn("Codigo");
        modeloTablaCotizacionProdutos.addColumn("Medida");
        modeloTablaCotizacionProdutos.addColumn("Nombre");
        modeloTablaCotizacionProdutos.addColumn("IVA");
        vcotizacionDetalle.getTablaProductosCotizacion().setModel(modeloTablaCotizacionProdutos);

        modeloTablaFactura.addColumn("Codigo");
        modeloTablaFactura.addColumn("Nombre");
        modeloTablaFactura.addColumn("Cantidad");
        modeloTablaFactura.addColumn("Precio");
        modeloTablaFactura.addColumn("Total");
        modeloTablaFactura.addColumn("IVA");
        vcotizacionDetalle.getTablaCotizacion().setModel(modeloTablaFactura);

    }

    public void validarCampos() {
//        validar.validarNumeros(vcotizacionDetalle.getTxtCedulaClienteIngreso());
//        validar.limitarCaracteres(vcotizacionDetalle.getTxtCedulaClienteIngreso(), 13);
    }

    public void iniciarVentana() {
        vcotizacionDetalle.show();
        validarCampos();
        vcotizacionDetalle.getTablaProductosCotizacion().getColumnModel().getColumn(0).setPreferredWidth(50);
        vcotizacionDetalle.getTablaProductosCotizacion().getColumnModel().getColumn(1).setPreferredWidth(80);
        vcotizacionDetalle.getTablaProductosCotizacion().getColumnModel().getColumn(2).setPreferredWidth(350);
        vcotizacionDetalle.getTablaProductosCotizacion().getColumnModel().getColumn(3).setPreferredWidth(13);

        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(0).setPreferredWidth(50);
        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(1).setPreferredWidth(350);
        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(2).setPreferredWidth(80);
        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(3).setPreferredWidth(50);
        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(4).setPreferredWidth(50);
        vcotizacionDetalle.getTablaCotizacion().getColumnModel().getColumn(5).setPreferredWidth(20);

        if (this.numeroPantalla == 1) {
            String codigoFactura = contextFacturaDetalle.RunCodigo();
            vcotizacionDetalle.getTxtNumeroCotizacion().setText(codigoFactura);
        }
        if (this.numeroPantalla == 2) {
            Cotizacion cotizacion = new Cotizacion();
            contextFacturaDetalle.RunvisualizeCotizacionDetalle(modeloTablaFactura, vcotizacionDetalle.getTxtNumeroCotizacion().getText());
            contextFacturaDetalle.RunvisualizarCliente(vcotizacionDetalle.getTxtFechaInicioCo(), vcotizacionDetalle.getTxtFechaFinCo(), vcotizacionDetalle.getTxtNumeroCotizacion().getText());
            cotizacion.setFechaInicioCotizacion(vcotizacionDetalle.getTxtFechaInicioCo().getText());
            cotizacion.setFechaFinCotizacion(vcotizacionDetalle.getTxtFechaFinCo().getText());
            contextFacturaDetalle.RunAnadir(cotizacion, 1);
            String codigoFactura = contextFacturaDetalle.RunCodigo();
            vcotizacionDetalle.getTxtNumeroCotizacion().setText(codigoFactura);

            int totalColumnaCompra = vcotizacionDetalle.getTablaCotizacion().getRowCount();
            vcotizacionDetalle.getTxtNumeroArticulos().setText(Integer.toString(totalColumnaCompra));
            sumarPrecioProductos();
        }
//        if(this.numeroPantalla==2)
//        {
//            vcotizacionDetalle.getBtn.setEnabled(true);
//            vcotizacionDetalle.getTablaFactura().setEnabled(false);
//            vcotizacionDetalle.getBtnCalcularFactura().setEnabled(false);
//            vcotizacionDetalle.getTablaProductosFactura().setEnabled(false);
//            contextFacturaDetalle.RunVisualizarAnular(modeloTablaFactura, vcotizacionDetalle.getTxtNumeroFactura().getText());
//            int totalColumnaCompra = vcotizacionDetalle.getTablaFactura().getRowCount();
//            vcotizacionDetalle.getTxtNumeroArticulos().setText(Integer.toString(totalColumnaCompra));
//            sumarPrecioProductos();        
//        }

        contextFacturaDetalle.RunVisualizar(modeloTablaCotizacionProdutos, 1);
        vcotizacionDetalle.getTxtBuscarProductosCotizacion().setDocument(new Validaciones());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(vcotizacionDetalle.getRbEfectivo());
        grupo.add(vcotizacionDetalle.getRbTarjeta());

    }

    public void cleanTable() {
        for (int i = 0; i < vcotizacionDetalle.getTablaProductosCotizacion().getRowCount(); i++) {
            modeloTablaCotizacionProdutos.removeRow(i);
            i -= 1;
        }
    }

    public void modificarCantidadRestaProducto(String cantidad, String codigoProducto) {

        float cantidadProOriginal = contextFacturaDetalle.RunCantidadProducto(codigoProducto);
        float cantidadProducto = Float.valueOf(cantidad);
        float totalProducto = 0;
        totalProducto = cantidadProOriginal - cantidadProducto;
        contextFacturaDetalle.RunModificarCantidad(totalProducto, codigoProducto);
    }

    public void modificarCantidadSumaProducto(String cantidad, String codigoProducto) {

        float cantidadProOriginal = contextFacturaDetalle.RunCantidadProducto(codigoProducto);
        float cantidadProducto = Float.valueOf(cantidad);
        float totalProducto = 0;
        totalProducto = cantidadProOriginal + cantidadProducto;
        contextFacturaDetalle.RunModificarCantidad(totalProducto, codigoProducto);
    }

    public void limpiarCampos() {
        // vcotizacionDetalle.getTxtCedulaClienteIngreso().setText("");
    }

    public int aumentarProductos(String codigo) {
        for (int j = 0; j < vcotizacionDetalle.getTablaCotizacion().getRowCount(); j++) {
            String codigoEnRenglon = (String) vcotizacionDetalle.getTablaCotizacion().getValueAt(j, 0);
            if (codigo.equals(codigoEnRenglon)) {
                return j;
            }
        }
        return -1;

    }

    public void sumarPrecioProductos() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcotizacionDetalle.getTablaCotizacion().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vcotizacionDetalle.getTablaCotizacion().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            vcotizacionDetalle.getTxtTotalFactura().setText(Double.toString(totalProductos1));
        }
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
        int filas = vcotizacionDetalle.getTablaCotizacion().getRowCount();

        for (int i = 0; i < filas; i++) {
            printer.printTextWrap(9 + i, 10, 1, 80, vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 0).toString() + "|" + vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 1).toString() + "| " + vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 2).toString() + "| " + vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 3).toString() + "|" + vcotizacionDetalle.getTablaCotizacion().getValueAt(i, 4).toString());
        }

        if (filas > 15) {
            printer.printCharAtCol(filas + 1, 1, 80, "=");
            printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + vcotizacionDetalle.getTxtTotalFactura().getText());
            printer.printCharAtCol(filas + 2, 1, 80, "=");
            printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
        } else {
            printer.printCharAtCol(25, 1, 80, "=");
            printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + vcotizacionDetalle.getTxtTotalFactura().getText());
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

        //  PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                //               printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(vprincipal, "No existen impresoras instaladas");
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();
    }

    public double subTotalCero() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcotizacionDetalle.getTablaCotizacion().getRowCount(); j++) {
            if (vcotizacionDetalle.getTablaCotizacion().getValueAt(j, 5).toString().equals("NO")) {
                totalProductos1 = totalProductos1 + Float.parseFloat(vcotizacionDetalle.getTablaCotizacion().getValueAt(j, 4).toString());
                totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            }
        }
        return totalProductos1;
    }

    public double sumarPrecioProductosTabla() {
        double totalProductos1 = 0;
        for (int j = 0; j < vcotizacionDetalle.getTablaCotizacion().getRowCount(); j++) {
            totalProductos1 = totalProductos1 + Float.parseFloat(vcotizacionDetalle.getTablaCotizacion().getValueAt(j, 4).toString());
            totalProductos1 = Math.round(totalProductos1 * 100) / 100d;
            vcotizacionDetalle.getTxtTotalFactura().setText(Double.toString(totalProductos1));
            return totalProductos1;
        }
        return 0;
    }

    public void calcularTotal() {

        if (vcotizacionDetalle.getRbTarjeta().isSelected() == false && vcotizacionDetalle.getRbEfectivo().isSelected() == false) {

            vcotizacionDetalle.getBtnCalcularFactura().setEnabled(true);

            JOptionPane.showMessageDialog(vprincipal, "Seleccione un metodo de Pago");
        } else if (vcotizacionDetalle.getRbEfectivo().isSelected() == true) {

            double subtotalcero = subTotalCero();
            double subTotal = Double.parseDouble(vcotizacionDetalle.getTxtTotalFactura().getText());
            double subIvaTotal = subTotal - subtotalcero;
            
            double subIvaTotaldosDecimales;
            subIvaTotaldosDecimales = Math.round(subIvaTotal * 100) / 100d;
            
            Object[] objectoSubtotal = new Object[6];
            objectoSubtotal[0] = " ";
            objectoSubtotal[1] = " "; 
            objectoSubtotal[2] = " ";
            objectoSubtotal[3] = "SUB 12%";
            objectoSubtotal[4] = subIvaTotaldosDecimales;
            modeloTablaFactura.addRow(objectoSubtotal);
            
            Object[] objectoIvaCero = new Object[6];
            objectoIvaCero[0] = " ";
            objectoIvaCero[1] = " ";
            objectoIvaCero[2] = " ";
            objectoIvaCero[3] = "SUB 0%";
            objectoIvaCero[4] = subtotalcero;
            modeloTablaFactura.addRow(objectoIvaCero);

            double ivaTotal = ((0.12 * subIvaTotal));
            ivaTotal = Math.round(ivaTotal * 100) / 100d;
            Object[] objectoIva = new Object[6];
            objectoIva[0] = " ";
            objectoIva[1] = " ";
            objectoIva[2] = " ";
            objectoIva[3] = "I.V.A 12%";
            objectoIva[4] = ivaTotal;
            modeloTablaFactura.addRow(objectoIva);

            double superTotal = (ivaTotal + subtotalcero + subIvaTotal);
            superTotal = Math.round(superTotal * 100) / 100d;
            Object[] objectoTotal = new Object[6];
            objectoTotal[0] = " ";
            objectoTotal[1] = " ";
            objectoTotal[2] = " ";
            objectoTotal[3] = "TOTAL";
            objectoTotal[4] = superTotal;
            modeloTablaFactura.addRow(objectoTotal);

            vcotizacionDetalle.getTxtTotalFactura().setText(Double.toString(superTotal));

            vcotizacionDetalle.getBtnCalcularFactura().setEnabled(false);
            vcotizacionDetalle.getBtnGenerarPDFCotizacion().setEnabled(true);
            vcotizacionDetalle.getTablaProductosCotizacion().setEnabled(false);
            vcotizacionDetalle.getBtnEditarTabla().setEnabled(true);

        } else if (vcotizacionDetalle.getRbTarjeta().isSelected() == true) {

            double subtotalcero = subTotalCero();
            double subTotal = Double.parseDouble(vcotizacionDetalle.getTxtTotalFactura().getText());
            double subIvaTotal = subTotal - subtotalcero;
            
            double subIvaTotaldosDecimales;
            subIvaTotaldosDecimales = Math.round(subIvaTotal * 100) / 100d;
            
            Object[] objectoSubtotal = new Object[6];
            objectoSubtotal[0] = " ";
            objectoSubtotal[1] = " "; 
            objectoSubtotal[2] = " ";
            objectoSubtotal[3] = "SUB 12%";
            objectoSubtotal[4] = subIvaTotaldosDecimales;
            modeloTablaFactura.addRow(objectoSubtotal);

            Object[] objectoIvaCero = new Object[6];
            objectoIvaCero[0] = " ";
            objectoIvaCero[1] = " ";
            objectoIvaCero[2] = " ";
            objectoIvaCero[3] = "SUB 0%";
            objectoIvaCero[4] = subtotalcero;
            modeloTablaFactura.addRow(objectoIvaCero);

            double ivaTotal = ((0.12 * subIvaTotal));
            ivaTotal = Math.round(ivaTotal * 100) / 100d;
            Object[] objectoIva = new Object[6];
            objectoIva[0] = " ";
            objectoIva[1] = " ";
            objectoIva[2] = " ";
            objectoIva[3] = "I.V.A 12%";
            objectoIva[4] = ivaTotal;
            modeloTablaFactura.addRow(objectoIva);

            double superTotal = (ivaTotal + subtotalcero + subIvaTotal);

            double ivaTarjeta = ((0.10 * superTotal));
            double superTotalTarjeta = (superTotal + ivaTarjeta);
            superTotalTarjeta = Math.round(superTotalTarjeta * 100) / 100d;

            Object[] objectoTotal = new Object[6];
            objectoTotal[0] = " ";
            objectoTotal[1] = " ";
            objectoTotal[2] = " ";
            objectoTotal[3] = "TOTAL";
            objectoTotal[4] = superTotalTarjeta;
            modeloTablaFactura.addRow(objectoTotal);

            vcotizacionDetalle.getTxtTotalFactura().setText(Double.toString(superTotalTarjeta));
            vcotizacionDetalle.getBtnCalcularFactura().setEnabled(false);
            vcotizacionDetalle.getBtnGenerarPDFCotizacion().setEnabled(true);
            vcotizacionDetalle.getTablaProductosCotizacion().setEnabled(false);
            vcotizacionDetalle.getBtnEditarTabla().setEnabled(true);

        }

    }

    //OJOOOOOO SI SE AUMENTA ALGO EN EL SUBTOTAL AUMETAR -3 DE IGUAL MANERA AL GUARDAR EL DETALLE FACTURA
    public void restarStock() {
        for (int fila = 0; fila < vcotizacionDetalle.getTablaCotizacion().getRowCount() - 4; fila++) {
            modificarCantidadRestaProducto(vcotizacionDetalle.getTablaCotizacion().getValueAt(fila, 2).toString(), vcotizacionDetalle.getTablaCotizacion().getValueAt(fila, 0).toString());
        }
    }

    public void aumentarStock() {
        for (int fila = 0; fila < vcotizacionDetalle.getTablaCotizacion().getRowCount(); fila++) {
            modificarCantidadSumaProducto(vcotizacionDetalle.getTablaCotizacion().getValueAt(fila, 2).toString(), vcotizacionDetalle.getTablaCotizacion().getValueAt(fila, 0).toString());
        }
    }

    public void quitarUltimasFilas() {

        modeloTablaFactura.removeRow(vcotizacionDetalle.getTablaCotizacion().getRowCount() - 1);
        modeloTablaFactura.removeRow(vcotizacionDetalle.getTablaCotizacion().getRowCount() - 1);
        modeloTablaFactura.removeRow(vcotizacionDetalle.getTablaCotizacion().getRowCount() - 1);
        modeloTablaFactura.removeRow(vcotizacionDetalle.getTablaCotizacion().getRowCount() - 1);
        vcotizacionDetalle.getTablaProductosCotizacion().setEnabled(true);
        vcotizacionDetalle.getBtnEditarTabla().setEnabled(false);
        vcotizacionDetalle.getBtnCalcularFactura().setEnabled(true);

        sumarPrecioProductos();

    }

    @Override
    public void keyReleased(KeyEvent ae) {
        if (vcotizacionDetalle.getTxtBuscarProductosCotizacion() == ae.getSource()) {
            trs.setRowFilter(RowFilter.regexFilter("(?i)" + vcotizacionDetalle.getTxtBuscarProductosCotizacion().getText(), 2));
        }

    }

    @Override
    public void keyTyped(KeyEvent ae) {

        if (vcotizacionDetalle.getTxtBuscarProductosCotizacion() == ae.getSource()) {
            trs = new TableRowSorter(modeloTablaCotizacionProdutos);
            vcotizacionDetalle.getTablaProductosCotizacion().setRowSorter(trs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed() && vcotizacionDetalle.getTablaProductosCotizacion() == e.getSource()) {
            float superTotal = 0.0f;
            try {
                int fila = vcotizacionDetalle.getTablaProductosCotizacion().getSelectedRow();
                if (fila >= 0) {

                    float precioProducto = contextFacturaDetalle.RunPrecioCodigo(vcotizacionDetalle.getTablaProductosCotizacion().getValueAt(fila, 0).toString());
                    String cantidad = JOptionPane.showInputDialog(vprincipal, "Intruduzca la cantidad");

                    float cantidadProducto = Float.parseFloat(cantidad);
                    int filaDuplicada = aumentarProductos(vcotizacionDetalle.getTablaProductosCotizacion().getValueAt(fila, 0).toString());

                    if (filaDuplicada >= 0) {
                        String totalCantidad = vcotizacionDetalle.getTablaCotizacion().getValueAt(filaDuplicada, 2).toString();
                        float tc = Float.parseFloat(totalCantidad);
                        tc = tc + cantidadProducto;

                        String precio = Float.toString(precioProducto);

                        double totalProducto = tc * precioProducto;
                        totalProducto = Math.round(totalProducto * 100) / 100d;
                        String to = Double.toString(totalProducto);

                        modeloTablaFactura.setValueAt(Float.toString(tc), filaDuplicada, 2);
                        modeloTablaFactura.setValueAt(to, filaDuplicada, 4);
                        modeloTablaFactura.fireTableRowsUpdated(filaDuplicada, filaDuplicada);
                    } else {
                        String precio = Float.toString(precioProducto);

                        double totalProducto = cantidadProducto * precioProducto;
                        totalProducto = Math.round(totalProducto * 100) / 100d;
                        String to = Double.toString(totalProducto);

                        String datosTabla[] = new String[7];
                        datosTabla[0] = vcotizacionDetalle.getTablaProductosCotizacion().getValueAt(fila, 0).toString();
                        datosTabla[1] = vcotizacionDetalle.getTablaProductosCotizacion().getValueAt(fila, 2).toString();
                        datosTabla[2] = cantidad;
                        datosTabla[3] = precio;
                        datosTabla[4] = to;
                        datosTabla[5] = vcotizacionDetalle.getTablaProductosCotizacion().getValueAt(fila, 3).toString();
                        modeloTablaFactura.addRow(datosTabla);

                        vcotizacionDetalle.getTablaProductosCotizacion().getSelectionModel().removeSelectionInterval(fila, fila);
                        vcotizacionDetalle.getTablaCotizacion().scrollRectToVisible(vcotizacionDetalle.getTablaCotizacion().getCellRect(vcotizacionDetalle.getTablaCotizacion().getRowCount() - 1, 0, true));
                    }

                    int totalColumnaCompra = vcotizacionDetalle.getTablaCotizacion().getRowCount();
                    vcotizacionDetalle.getTxtNumeroArticulos().setText(Integer.toString(totalColumnaCompra));
                    sumarPrecioProductos();

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
            }
        }
        if (e.getClickCount() == 2 && !e.isConsumed() && vcotizacionDetalle.getTablaCotizacion() == e.getSource()) {
            try {
                int fila = vcotizacionDetalle.getTablaCotizacion().getSelectedRow();
                if (fila >= 0) {
                    modeloTablaFactura.removeRow(fila);
                    int totalColumnaCompra = vcotizacionDetalle.getTablaCotizacion().getRowCount();
                    vcotizacionDetalle.getTxtNumeroArticulos().setText(Integer.toString(totalColumnaCompra));
                    sumarPrecioProductos();

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vprincipal, "No se Guardo");
            }
        }
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
        if (vcotizacionDetalle.getRbEfectivo().isSelected()) {
            metodo = 2;
            return metodo;
        }
        return metodo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.vcotizacionDetalle.getBtnGenerarPDFCotizacion() == ae.getSource()) {

            //restarStock();
            contextFacturaDetalle.RunAnadirCotizacionDetalle(modeloTablaFactura, vcotizacionDetalle.getTxtNumeroCotizacion().getText(), vprincipal.getLblCedula().getText(), Float.parseFloat(vcotizacionDetalle.getTxtTotalFactura().getText()));
            crearPDF();
//imprimirFactura();
            vcotizacionDetalle.dispose();
        }

        if (this.vcotizacionDetalle.getBtnCalcularFactura() == ae.getSource()) {

            calcularTotal();
        }

        if (this.vcotizacionDetalle.getBtnEditarTabla() == ae.getSource()) {
            vcotizacionDetalle.getBtnGenerarPDFCotizacion().setEnabled(false);
            quitarUltimasFilas();
        }

    }
}

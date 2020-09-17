
package facturacioncarniceria.vista;

import com.toedter.calendar.JDateChooser;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import rojeru_san.RSButtonRiple;
import rojerusan.RSDateChooser;

/**
 *
 * @author JORDY
 */
public class VReportes extends javax.swing.JInternalFrame {

    public RSButtonRiple getBtnExportar1() {
        return btnExportar1;
    }

    public void setBtnExportar1(RSButtonRiple btnExportar1) {
        this.btnExportar1 = btnExportar1;
    }

    public RSButtonRiple getBtnExportar2() {
        return btnExportar2;
    }

    public void setBtnExportar2(RSButtonRiple btnExportar2) {
        this.btnExportar2 = btnExportar2;
    }
    
    
    

    public RSButtonRiple getBtnActualizarCompra() {
        return btnActualizarCompra;
    }

    public void setBtnActualizarCompra(RSButtonRiple btnActualizarCompra) {
        this.btnActualizarCompra = btnActualizarCompra;
    }

    public JTable getTablaReporteCompra1() {
        return tablaReporteCompra1;
    }

    public void setTablaReporteCompra1(JTable tablaReporteCompra1) {
        this.tablaReporteCompra1 = tablaReporteCompra1;
    }

    public JTextField getTxtBuscarProducto() {
        return txtBuscarProducto;
    }

    public void setTxtBuscarProducto(JTextField txtBuscarProducto) {
        this.txtBuscarProducto = txtBuscarProducto;
    }

    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
        this.txtCodigoProducto = txtCodigoProducto;
    }

    public JTextField getTxtProveedorBuscar() {
        return txtProveedorBuscar;
    }

    public void setTxtProveedorBuscar(JTextField txtProveedorBuscar) {
        this.txtProveedorBuscar = txtProveedorBuscar;
    }
    
    

    public RSButtonRiple getBtnExportarCompras() {
        return btnExportarCompras;
    }

    public void setBtnExportarCompras(RSButtonRiple btnExportarCompras) {
        this.btnExportarCompras = btnExportarCompras;
    }

    public Label getCAJA1() {
        return CAJA1;
    }

    public void setCAJA1(Label CAJA1) {
        this.CAJA1 = CAJA1;
    }

    public RSButtonRiple getBtnActualizar1() {
        return btnActualizar1;
    }

    
    
    public void setBtnActualizar1(RSButtonRiple btnActualizar1) {
        this.btnActualizar1 = btnActualizar1;
    }

    public JDateChooser getDcFinal1() {
        return dcFinal1;
    }

    public void setDcFinal1(JDateChooser dcFinal1) {
        this.dcFinal1 = dcFinal1;
    }

    public JDateChooser getDcInicial1() {
        return dcInicial1;
    }

    public void setDcInicial1(JDateChooser dcInicial1) {
        this.dcInicial1 = dcInicial1;
    }

    public JTable getTablaReporteVenta1() {
        return tablaReporteVenta1;
    }

    public void setTablaReporteVenta1(JTable tablaReporteVenta1) {
        this.tablaReporteVenta1 = tablaReporteVenta1;
    }

    public JTextField getTxtCaja() {
        return txtCaja;
    }

    public void setTxtCaja(JTextField txtCaja) {
        this.txtCaja = txtCaja;
    }

    public JTextField getTxtNumFacturas() {
        return txtNumFacturas;
    }

    public void setTxtNumFacturas(JTextField txtNumFacturas) {
        this.txtNumFacturas = txtNumFacturas;
    }

    public RSButtonRiple getBtnExportarVentas() {
        return btnExportarVentas;
    }

    public void setBtnExportarVentas(RSButtonRiple btnExportarVentas) {
        this.btnExportarVentas = btnExportarVentas;
    }

    
    
    public RSButtonRiple getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(RSButtonRiple btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public JDateChooser getDcFinal() {
        return dcFinal;
    }

    public void setDcFinal(JDateChooser dcFinal) {
        this.dcFinal = dcFinal;
    }

    public JDateChooser getDcInicial() {
        return dcInicial;
    }

    public void setDcInicial(JDateChooser dcInicial) {
        this.dcInicial = dcInicial;
    }

    
    
    public JTable getTablaReporteCompra() {
        return tablaReporteCompra;
    }

    public void setTablaReporteCompra(JTable tablaReporteCompra) {
        this.tablaReporteCompra = tablaReporteCompra;
    }

    public JTable getTablaReporteVenta() {
        return tablaReporteVenta;
    }

    public void setTablaReporteVenta(JTable tablaReporteVenta) {
        this.tablaReporteVenta = tablaReporteVenta;
    }

    
    
   
        
    

    /**
     * Creates new form VProvider
     */
    public VReportes() {

        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlVisualizar = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaReporteVenta = new javax.swing.JTable();
        btnActualizar = new rojeru_san.RSButtonRiple();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaReporteCompra = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dcInicial = new com.toedter.calendar.JDateChooser();
        dcFinal = new com.toedter.calendar.JDateChooser();
        btnExportarCompras = new rojeru_san.RSButtonRiple();
        btnExportarVentas = new rojeru_san.RSButtonRiple();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaReporteVenta1 = new javax.swing.JTable();
        btnActualizar1 = new rojeru_san.RSButtonRiple();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dcInicial1 = new com.toedter.calendar.JDateChooser();
        dcFinal1 = new com.toedter.calendar.JDateChooser();
        txtCaja = new javax.swing.JTextField();
        CAJA = new java.awt.Label();
        txtNumFacturas = new javax.swing.JTextField();
        CAJA1 = new java.awt.Label();
        btnExportar1 = new rojeru_san.RSButtonRiple();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaReporteCompra1 = new javax.swing.JTable();
        btnActualizarCompra = new rojeru_san.RSButtonRiple();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtProveedorBuscar = new javax.swing.JTextField();
        btnExportar2 = new rojeru_san.RSButtonRiple();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        pnlVisualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        pnlVisualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pnlVisualizar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pnlVisualizarFocusGained(evt);
            }
        });
        pnlVisualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlVisualizarMouseClicked(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        tablaReporteVenta.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaReporteVenta.setForeground(new java.awt.Color(0, 0, 255));
        tablaReporteVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaReporteVenta);

        btnActualizar.setBackground(new java.awt.Color(0, 204, 0));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        tablaReporteCompra.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaReporteCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tablaReporteCompra);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("VENTAS");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("COMPRAS");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("FECHA INICIAL");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("FECHA FINAL");

        dcInicial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dcFinal.setDoubleBuffered(false);
        dcFinal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnExportarCompras.setBackground(new java.awt.Color(255, 153, 0));
        btnExportarCompras.setText("Exportar Compras");
        btnExportarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarComprasActionPerformed(evt);
            }
        });

        btnExportarVentas.setBackground(new java.awt.Color(255, 153, 0));
        btnExportarVentas.setText("Exportar Ventas");
        btnExportarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnExportarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288)
                        .addComponent(btnExportarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(dcFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportarCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        pnlVisualizar.addTab("Reportes", jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        tablaReporteVenta1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaReporteVenta1.setForeground(new java.awt.Color(0, 0, 255));
        tablaReporteVenta1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(tablaReporteVenta1);

        btnActualizar1.setBackground(new java.awt.Color(0, 204, 0));
        btnActualizar1.setText("Actualizar");
        btnActualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("VENTAS");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("FECHA INICIAL");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("FECHA FINAL");

        dcInicial1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        dcFinal1.setDoubleBuffered(false);
        dcFinal1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtCaja.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CAJA.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        CAJA.setText("CAJA");

        txtNumFacturas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNumFacturas.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CAJA1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        CAJA1.setText("Num Facturas");

        btnExportar1.setBackground(new java.awt.Color(255, 153, 0));
        btnExportar1.setText("Exportar");
        btnExportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 437, Short.MAX_VALUE)
                        .addComponent(dcFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(CAJA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(CAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcInicial1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(dcFinal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CAJA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtNumFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CAJA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28))
        );

        pnlVisualizar.addTab("Ventas/Caja", jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        tablaReporteCompra1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaReporteCompra1.setForeground(new java.awt.Color(0, 0, 255));
        tablaReporteCompra1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(tablaReporteCompra1);

        btnActualizarCompra.setBackground(new java.awt.Color(0, 204, 0));
        btnActualizarCompra.setText("Actualizar");
        btnActualizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCompraActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("COMPRAS");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Producto:");

        txtBuscarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Codigo:");

        txtCodigoProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Proveedor:");

        txtProveedorBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnExportar2.setBackground(new java.awt.Color(255, 153, 0));
        btnExportar2.setText("Exportar");
        btnExportar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtProveedorBuscar))
                    .addComponent(jScrollPane8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(btnActualizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                .addComponent(btnExportar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(296, 296, 296))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtProveedorBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pnlVisualizar.addTab("Compras", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVisualizar)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 595, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnExportarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarComprasActionPerformed

    private void btnExportarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarVentasActionPerformed

    private void pnlVisualizarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pnlVisualizarFocusGained
        // limpiarLogin();
        // limpiarRegistro();
        //txtCedulaLogin.grabFocus();
    }//GEN-LAST:event_pnlVisualizarFocusGained

    private void pnlVisualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlVisualizarMouseClicked

    }//GEN-LAST:event_pnlVisualizarMouseClicked

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnActualizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarCompraActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportar1ActionPerformed

    private void btnExportar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label CAJA;
    private java.awt.Label CAJA1;
    private rojeru_san.RSButtonRiple btnActualizar;
    private rojeru_san.RSButtonRiple btnActualizar1;
    private rojeru_san.RSButtonRiple btnActualizarCompra;
    private rojeru_san.RSButtonRiple btnExportar1;
    private rojeru_san.RSButtonRiple btnExportar2;
    private rojeru_san.RSButtonRiple btnExportarCompras;
    private rojeru_san.RSButtonRiple btnExportarVentas;
    private com.toedter.calendar.JDateChooser dcFinal;
    private com.toedter.calendar.JDateChooser dcFinal1;
    private com.toedter.calendar.JDateChooser dcInicial;
    private com.toedter.calendar.JDateChooser dcInicial1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane pnlVisualizar;
    private javax.swing.JTable tablaReporteCompra;
    private javax.swing.JTable tablaReporteCompra1;
    private javax.swing.JTable tablaReporteVenta;
    private javax.swing.JTable tablaReporteVenta1;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtNumFacturas;
    private javax.swing.JTextField txtProveedorBuscar;
    // End of variables declaration//GEN-END:variables
}

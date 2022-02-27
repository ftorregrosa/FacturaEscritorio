
package facturacion.vista;

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

        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaReporteVenta1 = new javax.swing.JTable();
        btnActualizar1 = new rojeru_san.RSButtonRiple();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dcInicial1 = new com.toedter.calendar.JDateChooser();
        dcFinal1 = new com.toedter.calendar.JDateChooser();
        btnExportar1 = new rojeru_san.RSButtonRiple();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
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
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane7)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
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
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizar1ActionPerformed

    private void btnExportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButtonRiple btnActualizar1;
    private rojeru_san.RSButtonRiple btnExportar1;
    private com.toedter.calendar.JDateChooser dcFinal1;
    private com.toedter.calendar.JDateChooser dcInicial1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable tablaReporteVenta1;
    // End of variables declaration//GEN-END:variables
}

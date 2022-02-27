
package facturacion.vista;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import rojerusan.RSButtonMetro;


public class VPrincipal extends javax.swing.JFrame {
    
    int x,y;

    public RSButtonMetro getInformes() {
        return informes;
    }

    public void setInformes(RSButtonMetro informes) {
        this.informes = informes;
    }
    
    public RSButtonMetro getCliente() {
        return cliente;
    }

    public void setCliente(RSButtonMetro cliente) {
        this.cliente = cliente;
    }

    public RSButtonMetro getFactura() {
        return factura;
    }

    public void setFactura(RSButtonMetro factura) {
        this.factura = factura;
    }

    public RSButtonMetro getInventariio() {
        return inventariio;
    }

    public void setInventariio(RSButtonMetro inventariio) {
        this.inventariio = inventariio;
    }

    public JLabel getLblCedula() {
        return lblCedula;
    }

    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    public JLabel getLblNombreUsuario() {
        return lblNombreUsuario;
    }

    public void setLblNombreUsuario(JLabel lblNombreUsuario) {
        this.lblNombreUsuario = lblNombreUsuario;
    }

    public JPanel getPnlMenu() {
        return pnlMenu;
    }

    public void setPnlMenu(JPanel pnlMenu) {
        this.pnlMenu = pnlMenu;
    }

    public JDesktopPane getPnlPrincipal() {
        return pnlPrincipal;
    }

    public void setPnlPrincipal(JDesktopPane pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }
    
    

    public VPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        factura = new rojerusan.RSButtonMetro();
        inventariio = new rojerusan.RSButtonMetro();
        cliente = new rojerusan.RSButtonMetro();
        informes = new rojerusan.RSButtonMetro();
        panelMenu = new javax.swing.JPanel();
        lblNombreUsuario = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        pnlPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1370, 760));
        setPreferredSize(new java.awt.Dimension(1370, 760));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1035, 720));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu.setMaximumSize(new java.awt.Dimension(153, 540));
        pnlMenu.setPreferredSize(new java.awt.Dimension(153, 540));

        factura.setBackground(new java.awt.Color(255, 255, 255));
        factura.setForeground(new java.awt.Color(0, 112, 192));
        factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacion/imagenes/proyecto.png"))); // NOI18N
        factura.setText("Facturas");
        factura.setToolTipText("");
        factura.setHideActionText(true);
        factura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        factura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        inventariio.setBackground(new java.awt.Color(255, 255, 255));
        inventariio.setForeground(new java.awt.Color(0, 112, 192));
        inventariio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacion/imagenes/inventario.png"))); // NOI18N
        inventariio.setText("Inventario");
        inventariio.setToolTipText("");
        inventariio.setHideActionText(true);
        inventariio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inventariio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cliente.setBackground(new java.awt.Color(255, 255, 255));
        cliente.setForeground(new java.awt.Color(0, 112, 192));
        cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacion/imagenes/AgregarUsuario.png"))); // NOI18N
        cliente.setText("Cliente");
        cliente.setToolTipText("");
        cliente.setHideActionText(true);
        cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        informes.setBackground(new java.awt.Color(255, 255, 255));
        informes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        informes.setForeground(new java.awt.Color(0, 112, 192));
        informes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacion/imagenes/Reportes.png"))); // NOI18N
        informes.setText("Reportes");
        informes.setToolTipText("");
        informes.setHideActionText(true);
        informes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        informes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventariio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addComponent(informes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventariio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 8.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(pnlMenu, gridBagConstraints);

        panelMenu.setBackground(new java.awt.Color(0, 112, 192));
        panelMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        panelMenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelMenuMouseDragged(evt);
            }
        });
        panelMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelMenuMousePressed(evt);
            }
        });

        lblNombreUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblCedula.setBackground(new java.awt.Color(255, 255, 255));
        lblCedula.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCedula.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNombreUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(panelMenu, gridBagConstraints);

        pnlCentro.setBackground(new java.awt.Color(0, 112, 192));

        pnlPrincipal.setMaximumSize(new java.awt.Dimension(1600, 900));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2246, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlCentroLayout = new javax.swing.GroupLayout(pnlCentro);
        pnlCentro.setLayout(pnlCentroLayout);
        pnlCentroLayout.setHorizontalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCentroLayout.setVerticalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentroLayout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(pnlCentro, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblReportesActionPerformed

    }//GEN-LAST:event_lblReportesActionPerformed

    private void lblReportesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportesMousePressed

    }//GEN-LAST:event_lblReportesMousePressed

    private void lblPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblPedidosActionPerformed

     
    }//GEN-LAST:event_lblPedidosActionPerformed

    private void lblPedidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPedidosMousePressed

    }//GEN-LAST:event_lblPedidosMousePressed

    private void lblCotizacionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCotizacionesMousePressed

    }//GEN-LAST:event_lblCotizacionesMousePressed

    private void lblCotizacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCotizacionesActionPerformed
     
    }//GEN-LAST:event_lblCotizacionesActionPerformed

    private void lblKardexMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKardexMousePressed

    }//GEN-LAST:event_lblKardexMousePressed

    private void lblKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblKardexActionPerformed
       
    }//GEN-LAST:event_lblKardexActionPerformed

    private void lblCostosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCostosMousePressed

    }//GEN-LAST:event_lblCostosMousePressed

    private void lblCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCostosActionPerformed
     
    }//GEN-LAST:event_lblCostosActionPerformed

    private void lblDevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblDevolucionesActionPerformed
       
    }//GEN-LAST:event_lblDevolucionesActionPerformed

    private void lblDevolucionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDevolucionesMousePressed
       
    }//GEN-LAST:event_lblDevolucionesMousePressed

    private void lblInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInventarioMousePressed
        
    }//GEN-LAST:event_lblInventarioMousePressed

    private void lblInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblInventarioActionPerformed
 
    }//GEN-LAST:event_lblInventarioActionPerformed

    private void panelMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelMenuMousePressed

    private void panelMenuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuMouseDragged
        Point mueve = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(mueve.x - x, mueve.y - y);
    }//GEN-LAST:event_panelMenuMouseDragged

    private void lblProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProveedorMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProveedorMousePressed

    private void lblProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblProveedorActionPerformed

    private void lblComprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblComprasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblComprasMousePressed

    private void lblComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblComprasActionPerformed

    private void lblUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsuarioActionPerformed

    private void lblUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsuarioMousePressed

    private void lblModificarUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarUsuarioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblModificarUsuarioMousePressed

    private void lblModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblModificarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblModificarUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro cliente;
    private rojerusan.RSButtonMetro factura;
    private rojerusan.RSButtonMetro informes;
    private rojerusan.RSButtonMetro inventariio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JDesktopPane pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}

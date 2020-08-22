
package facturacioncarniceria.vista;

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

    public RSButtonMetro getCompra() {
        return compra;
    }

    public void setCompra(RSButtonMetro compra) {
        this.compra = compra;
    }

    public RSButtonMetro getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(RSButtonMetro cotizacion) {
        this.cotizacion = cotizacion;
    }

    public RSButtonMetro getProveedor() {
        return proveedor;
    }

    public void setProveedor(RSButtonMetro proveedor) {
        this.proveedor = proveedor;
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

    public RSButtonMetro getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(RSButtonMetro nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public RSButtonMetro getUsuario() {
        return usuario;
    }

    public void setUsuario(RSButtonMetro usuario) {
        this.usuario = usuario;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nuevoUsuario = new rojerusan.RSButtonMetro();
        factura = new rojerusan.RSButtonMetro();
        usuario = new rojerusan.RSButtonMetro();
        inventariio = new rojerusan.RSButtonMetro();
        compra = new rojerusan.RSButtonMetro();
        cotizacion = new rojerusan.RSButtonMetro();
        cliente = new rojerusan.RSButtonMetro();
        proveedor = new rojerusan.RSButtonMetro();
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

        pnlMenu.setBackground(new java.awt.Color(239, 238, 244));
        pnlMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));
        pnlMenu.setMaximumSize(new java.awt.Dimension(153, 540));
        pnlMenu.setPreferredSize(new java.awt.Dimension(153, 540));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VENTAS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("BODEGA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("USUARIO");

        nuevoUsuario.setBackground(new java.awt.Color(239, 238, 244));
        nuevoUsuario.setForeground(new java.awt.Color(128, 128, 131));
        nuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/ModificarUsuario.png"))); // NOI18N
        nuevoUsuario.setText("Nuevo Usuario");
        nuevoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nuevoUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        factura.setBackground(new java.awt.Color(239, 238, 244));
        factura.setForeground(new java.awt.Color(128, 128, 131));
        factura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/proyecto.png"))); // NOI18N
        factura.setText("Facturas");
        factura.setToolTipText("");
        factura.setHideActionText(true);
        factura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        factura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        usuario.setBackground(new java.awt.Color(239, 238, 244));
        usuario.setForeground(new java.awt.Color(128, 128, 131));
        usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/proyecto.png"))); // NOI18N
        usuario.setText("Usuario");
        usuario.setToolTipText("");
        usuario.setHideActionText(true);
        usuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        inventariio.setBackground(new java.awt.Color(239, 238, 244));
        inventariio.setForeground(new java.awt.Color(128, 128, 131));
        inventariio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/inventario.png"))); // NOI18N
        inventariio.setText("Inventario");
        inventariio.setToolTipText("");
        inventariio.setHideActionText(true);
        inventariio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inventariio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        compra.setBackground(new java.awt.Color(239, 238, 244));
        compra.setForeground(new java.awt.Color(128, 128, 131));
        compra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/compras.png"))); // NOI18N
        compra.setText("Compra");
        compra.setToolTipText("");
        compra.setHideActionText(true);
        compra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        compra.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraActionPerformed(evt);
            }
        });

        cotizacion.setBackground(new java.awt.Color(239, 238, 244));
        cotizacion.setForeground(new java.awt.Color(128, 128, 131));
        cotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/cotizaciones.png"))); // NOI18N
        cotizacion.setText("Cotización");
        cotizacion.setToolTipText("");
        cotizacion.setHideActionText(true);
        cotizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cotizacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cliente.setBackground(new java.awt.Color(239, 238, 244));
        cliente.setForeground(new java.awt.Color(128, 128, 131));
        cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/AgregarUsuario.png"))); // NOI18N
        cliente.setText("Cliente");
        cliente.setToolTipText("");
        cliente.setHideActionText(true);
        cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        proveedor.setBackground(new java.awt.Color(239, 238, 244));
        proveedor.setForeground(new java.awt.Color(128, 128, 131));
        proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/proveedor.png"))); // NOI18N
        proveedor.setText("Proveedor");
        proveedor.setToolTipText("");
        proveedor.setHideActionText(true);
        proveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        proveedor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });

        informes.setBackground(new java.awt.Color(239, 238, 244));
        informes.setForeground(new java.awt.Color(128, 128, 131));
        informes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facturacioncarniceria/imagenes/Reportes.png"))); // NOI18N
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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inventariio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(compra, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informes, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(nuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(informes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(factura, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(inventariio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(compra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 8.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(pnlMenu, gridBagConstraints);

        panelMenu.setBackground(new java.awt.Color(38, 86, 186));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
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

        pnlCentro.setBackground(new java.awt.Color(38, 86, 186));

        pnlPrincipal.setMaximumSize(new java.awt.Dimension(1600, 900));

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
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

    private void compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compraActionPerformed

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorActionPerformed

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
    private rojerusan.RSButtonMetro compra;
    private rojerusan.RSButtonMetro cotizacion;
    private rojerusan.RSButtonMetro factura;
    private rojerusan.RSButtonMetro informes;
    private rojerusan.RSButtonMetro inventariio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblNombreUsuario;
    private rojerusan.RSButtonMetro nuevoUsuario;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JDesktopPane pnlPrincipal;
    private rojerusan.RSButtonMetro proveedor;
    private rojerusan.RSButtonMetro usuario;
    // End of variables declaration//GEN-END:variables
}

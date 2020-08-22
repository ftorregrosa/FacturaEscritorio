/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import java.awt.Dimension;
import facturacioncarniceria.estrategia.Context;
import facturacioncarniceria.vista.VPrincipal;
import facturacioncarniceria.vista.VComprasIngreso;
import facturacioncarniceria.vista.VComprasHacer;
import facturacioncarniceria.modelo.Validaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import facturacioncarniceria.estrategia.ComprasEstrategia;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/**
 *
 * @author JORDY
 */
public class CCompraIngreso implements ActionListener {

    VComprasIngreso vcomprasIngreso;
    Context contextCompra;
    VPrincipal vmain;
    Validaciones validar = new Validaciones();
    ComprasEstrategia strategyCompra = new ComprasEstrategia();
     public static boolean controlVentanaPedidos = false;
    
    public CCompraIngreso(Context context, VComprasIngreso vcomprasIngreso, VPrincipal vmain) {
        this.vcomprasIngreso = vcomprasIngreso;
        this.contextCompra = context;
        this.vmain = vmain;
        this.vcomprasIngreso.getBtnGuardaFactura().addActionListener(this); 
        this.contextCompra = new Context(strategyCompra);
        validar.crearCaracteres(vcomprasIngreso.getTxtNumFactura());
    }

    public void iniciarVentana() {  
        vcomprasIngreso.show();
        contextCompra.RunLlenarCombo(vcomprasIngreso.getCbNombreProveedor());
        AutoCompleteDecorator.decorate(vcomprasIngreso.getCbNombreProveedor());
        validar.limitarCaracteres(vcomprasIngreso.getTxtNumFactura(), 17);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.vcomprasIngreso.getBtnGuardaFactura() == ae.getSource()) {
            
            vcomprasIngreso.dispose();

                VComprasHacer vcomprasHacer = new VComprasHacer();
                vcomprasHacer.setVisible(true);
                            
                vmain.getPnlPrincipal().add(vcomprasHacer);
                vcomprasHacer.toFront();
                Dimension desktopSize = vmain.getPnlPrincipal().getSize();
                Dimension FrameSize = vcomprasHacer.getSize();
                vcomprasHacer.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);

                try {
                    vcomprasHacer.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                CComprasHacer controladorPedidos = new CComprasHacer(contextCompra, vcomprasHacer, vmain);
                if (controlVentanaPedidos == false) {
                    vcomprasHacer.getTxtNombreProveedor().setText(vcomprasIngreso.getCbNombreProveedor().getSelectedItem().toString());
                    vcomprasHacer.getTxtNumeroFactura().setText(vcomprasIngreso.getTxtNumFactura().getText());
                 //   vcomprasHacer.getTxtProyectoNombreCotizacion().setText(vcotizacion.getCbNombreProyectoCotizacion().getSelectedItem().toString());
                    controladorPedidos.iniciarVentana();
                }
            
        }
    }
}



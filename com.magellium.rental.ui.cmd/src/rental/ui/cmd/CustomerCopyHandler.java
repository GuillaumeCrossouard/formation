package rental.ui.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.magellium.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

@Deprecated
public class CustomerCopyHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Clipboard clipboard = new Clipboard(Display.getCurrent());

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);

		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection) currentSelection;

			Customer firstElement = (Customer) isel.getFirstElement();

			// String textData = "Hello World";
			// String rtfData = "{\\rtf1\\b\\i Hello World}";

			String textData = firstElement.getDisplayName();
			String rtfData = "{\\rtf1\\b\\i " + textData + "}";
			Image image = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);

			Transfer[] transfers = new Transfer[] { TextTransfer.getInstance(), RTFTransfer.getInstance(),
					ImageTransfer.getInstance() };

			Object[] data = new Object[] { textData, rtfData, image.getImageData() };

			clipboard.setContents(data, transfers);
			clipboard.dispose();
		}
		return null;
	}

}

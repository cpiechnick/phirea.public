package test.graphiti.nonemf.rcpapp.draganddrop;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;

import test.graphiti.nonemf.domainmodel.INonEmfDomainObject;

public class DragListener implements DragSourceListener
{
  private final TreeViewer treeViewer;

  public DragListener(TreeViewer treeViewer)
  {
    this.treeViewer = treeViewer;
  }

  @Override
  public void dragFinished(DragSourceEvent event)
  {
    System.out.println("dragFinisched: " + event);

  }

  @Override
  public void dragSetData(DragSourceEvent event)
  {
    System.out.println("dragSetData: " + event);

    // Here you do the convertion to the type which is expected.
    IStructuredSelection selection = (IStructuredSelection) this.treeViewer.getSelection();
    Object firstElement = selection.getFirstElement();

    if(firstElement instanceof MyModel)
    {
      MyModel myData = (MyModel) firstElement;
      if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
//        event.data = myData;
        event.data = myData.getName() + "," + myData.getDescription();
      }
      if(MyModelTransfer.getInstance().isSupportedType(event.dataType))
        event.data = myData;
      if(LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType))
      {
    	LocalSelectionTransfer.getTransfer().setSelection(selection);
    	System.out.println("dragSetData, data set to " + LocalSelectionTransfer.getTransfer());
      }
    }
    
    if(firstElement instanceof INonEmfDomainObject)
    {
      INonEmfDomainObject myData = (INonEmfDomainObject) firstElement;
      if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
//        event.data = myData;
        event.data = myData.getId();
      }
      if(MyModelTransfer.getInstance().isSupportedType(event.dataType))
        event.data = myData;
      if(LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType))
      {
    	LocalSelectionTransfer.getTransfer().setSelection(selection);
    	System.out.println("dragSetData, data set to " + LocalSelectionTransfer.getTransfer());
      }
    }



  }

  @Override
  public void dragStart(DragSourceEvent event)
  {
    System.out.println("dragStart: " + event);

  }

}

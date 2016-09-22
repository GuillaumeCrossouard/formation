package com.magellium.rental.rcp;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.actions.ActionFactory;

public class RentalActionBarAdvisor extends ActionBarAdvisor {
	private IAction preferencesAction;
	private IAction quitAction;

	public RentalActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
		
		MenuManager menuManager = new MenuManager("New MenuManager");
		menuManager.setMenuText("File");
		menuBar.add(menuManager);
		menuManager.add(quitAction);
		
		MenuManager menuManager_1 = new MenuManager("New MenuManager");
		menuManager_1.setMenuText("Window");
		menuBar.add(menuManager_1);
		menuManager_1.add(preferencesAction);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		{
			preferencesAction = ActionFactory.PREFERENCES.create(window);
			register(preferencesAction);
		}
		{
			quitAction = ActionFactory.QUIT.create(window);
			register(quitAction);
		}
	}

}

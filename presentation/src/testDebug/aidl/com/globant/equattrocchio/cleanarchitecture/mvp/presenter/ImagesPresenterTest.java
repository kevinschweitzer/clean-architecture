package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ImagesPresenterTest {

    @Mock ImagesView view;
    @Mock GetLatestImagesUseCase getLatestImagesUseCase;
    @Mock GetImageByIdUseCase getImageByIdUseCase;
    @Mock SaveImagesUseCase saveImagesUseCase;
    @Mock RefreshImagesUseCase refreshImagesUseCase;
    ArgumentCaptor<CallServiceButtonObserver> CallServiceButtonObserverCaptor;
    private ImagesPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view,getLatestImagesUseCase,getImageByIdUseCase,saveImagesUseCase,refreshImagesUseCase);
    }

    @Test
    public void serviceTest(){

    }


}
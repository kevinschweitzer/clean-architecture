import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;
import com.globant.equattrocchio.domain.model.Image;

import net.bytebuddy.matcher.NullMatcher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.anything;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ImagesPresenterTest {

    private final static int RESOURCE_ID = R.string.app_name;
    @Mock ImagesView view;
    @Mock GetLatestImagesUseCase getLatestImagesUseCase;
    @Mock GetImageByIdUseCase getImageByIdUseCase;
    @Mock SaveImagesUseCase saveImagesUseCase;
    @Mock RefreshImagesUseCase refreshImagesUseCase;
    @Mock List<Image> images;
    @Captor ArgumentCaptor<CallServiceButtonObserver> callServiceButtonObserverCaptor;
    @Captor ArgumentCaptor<DisposableObserver<List<Image>>> listImagesObserverCaptor;
    @Captor ArgumentCaptor<DisposableObserver<Boolean>> booleanObserverCaptor;
    private ImagesPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view,getLatestImagesUseCase,getImageByIdUseCase,saveImagesUseCase,refreshImagesUseCase);
    }

   @Test
    public void onCallServiceButtonPressedTest() {
        presenter.onCallServiceButtonPressed();

        verify(getLatestImagesUseCase).execute(listImagesObserverCaptor.capture(),eq((Void)null));

        listImagesObserverCaptor.getValue().onNext(images);
        verify(view).setImages(images);
    }


    @Test
    public void setImagesInViewTest(){
        presenter.setImagesInView(images);

        verify(view).setImages(images);
    }

    @Test
    public void onImagePressedTest(){
        presenter.onImagePressed(1);

        verify(view).showImageDialog(1);
    }

    @Test
    public void onRefreshClickedTest(){
        presenter.onRefreshClicked();

        verify(saveImagesUseCase).execute(booleanObserverCaptor.capture(),eq((Void)null));
    }

    @Test
    public void refreshClickedSuccessTest(){
        presenter.onNextRefreshClicked(true);

        verify(view).showToast(R.string.refresh_success);
    }

    @Test
    public void refreshClickedFailTest(){
        presenter.onNextRefreshClicked(false);

        verify(view).showToast(R.string.refresh_error);
    }

    @Test
    public void showErrorTest(){
        presenter.showError(RESOURCE_ID);

        verify(view).showToast(RESOURCE_ID);
    }

    @Test
    public void conserveInstanceTest(){
        when(refreshImagesUseCase.getImagesFromRepository()).thenReturn(images);
        presenter.conserveInstance();

        verify(refreshImagesUseCase).getImagesFromRepository();
        verify(view).setImages(images);
    }


}
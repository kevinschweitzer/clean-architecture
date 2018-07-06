import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageDialogPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogView;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.RefreshImagesUseCase;
import com.globant.equattrocchio.domain.model.CompleteImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImageDialogPresenterTest {

    @Mock ImageDialogView view;
    @Mock RefreshImagesUseCase refreshImagesUseCase;
    @Mock GetImageByIdUseCase getImageByIdUseCase;
    @Captor ArgumentCaptor<DisposableObserver<CompleteImage>> completeImageObserverCaptor;
    @Mock CompleteImage image;
    @Mock ImageDialogPresenter presenter;
    private final static long imageId = 1;
    private final static int resourceId = R.string.app_name;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ImageDialogPresenter(view,getImageByIdUseCase,refreshImagesUseCase,imageId);
    }

    @Test
    public void getImageByIdTest() {
        //Don't need to call the method because it is called in the constructor
        verify(getImageByIdUseCase).execute(completeImageObserverCaptor.capture(),eq(imageId));
        completeImageObserverCaptor.getValue().onNext(image);
        verify(view).showImage(image);
    }

    @Test
    public void showImageInFragmentTest() {
        presenter.showImageInFragment(image);

        verify(view).showImage(image);
    }

    @Test
    public void showErrorTest() {
        presenter.showError(resourceId);

        verify(view).showMessage(resourceId);
    }

    @Test
    public void onDeleteClickedTest() {
        presenter.onDeleteClicked();

        verify(refreshImagesUseCase).delete(imageId);
        verify(view).hide();
    }
}
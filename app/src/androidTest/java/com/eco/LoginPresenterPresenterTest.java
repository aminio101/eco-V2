package com.eco;

import android.app.Application;
import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.eco.interfaces.ILoginView;
import com.eco.presenters.LoginPresenter;
import com.eco.rest.MethodApi;

import static androidx.test.InstrumentationRegistry.getInstrumentation;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class LoginPresenterPresenterTest {
    Context context = null;
    @Mock
    LoginPresenter loginPresenter;
//    ILoginPresenter loginPresenter;
    @Mock
    public ILoginView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.context = InstrumentationRegistry.getContext();
        MethodApi.init((Application) InstrumentationRegistry
                .getTargetContext()
                .getApplicationContext());
//        loginPresenter = new LoginPresenter(this.view, context, null, null);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {
        loginPresenter.sendCode("09196675357");
        verify(view).goToVeryFyCodeActivity();
    }
}

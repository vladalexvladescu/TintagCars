package dagger.component;

import com.example.vlad.tintagcars.MainActivity;
import com.example.vlad.tintagcars.MyProfileActivity;
import com.example.vlad.tintagcars.PostsAdapterMyProfile;
import com.example.vlad.tintagcars.PreviewActivity;

import javax.inject.Singleton;

import dagger.Component;
import dagger.module.AppModule;
import dagger.module.NetModule;

/**
 * Created by Vlad on 3/30/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(PreviewActivity activity);
    void inject(MyProfileActivity activity);
    void inject(PostsAdapterMyProfile adapterMyProfile);

}

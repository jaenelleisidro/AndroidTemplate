package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

/**
 * Fragment which houses the View pager.
 */
public class SweetDialogFragment extends BaseFragment {
    @InjectView(R.id.basic_test)
    View basicTest;
    private int i = -1;

    public View onCreateView2(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sweetdialog, container, false);
    }

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState2(Bundle outState) {
    }


    @OnClick(R.id.basic_test)
    public void basicTest(Button btnBasicButton) {
        SweetAlertDialog sd = new SweetAlertDialog(getActivity());
        sd.setCancelable(true);
        sd.setCanceledOnTouchOutside(true);
        sd.show();
    }
    @OnClick(R.id.under_text_test)
    public void underTextTest(Button btnUnderTextTest) {
        new SweetAlertDialog(getActivity())
                .setContentText("It's pretty, isn't it?")
                .show();

    }
    @OnClick(R.id.error_text_test)
    public void errorTextTest(Button errorTextTest) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show();

    }
    @OnClick(R.id.success_text_test)
    public void successTextTest(Button successTextTest) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Good job!")
                .setContentText("You clicked the button!")
                .show();

    }
    @OnClick(R.id.warning_confirm_test)
    public void warningConfirmTest(Button warningConfirmTest) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();

    }
    @OnClick(R.id.warning_cancel_test)
    public void warningCancelTest(Button warningCancelTest) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setCancelText("No,cancel plx!")
                .setConfirmText("Yes,delete it!")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("Cancelled!")
                                .setContentText("Your imaginary file is safe :)")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                        // or you can new a SweetAlertDialog to show
                               /* sDialog.dismiss();
                                new SweetAlertDialog(SampleActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Cancelled!")
                                        .setContentText("Your imaginary file is safe :)")
                                        .setConfirmText("OK")
                                        .show();*/
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();

    }
    @OnClick(R.id.custom_img_test)
    public void customImgTest(Button customImgTest) {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText("Sweet!")
                .setContentText("Here's a custom image.")
                .setCustomImage(R.drawable.ic_launcher)
                .show();

    }
    @OnClick(R.id.progress_dialog)
    public void progressDialog(Button progressDialog) {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(800 * 7, 800) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i){
                    case 0:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                }
            }

            public void onFinish() {
                i = -1;
                pDialog.setTitleText("Success!")
                        .setConfirmText("OK")
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        }.start();

    }

    public static Fragment newInstance(){
        return newInstance(R.layout.fragment_button);
    }

    public static Fragment newInstance(int layoutId){
        SweetDialogFragment simpleFragment =new SweetDialogFragment();
        return simpleFragment;
    }
}
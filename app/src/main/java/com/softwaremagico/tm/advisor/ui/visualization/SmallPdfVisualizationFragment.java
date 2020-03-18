package com.softwaremagico.tm.advisor.ui.visualization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.itextpdf.text.DocumentException;
import com.softwaremagico.tm.InvalidXmlElementException;
import com.softwaremagico.tm.advisor.CharacterManager;
import com.softwaremagico.tm.advisor.R;
import com.softwaremagico.tm.advisor.log.AdvisorLog;
import com.softwaremagico.tm.pdf.complete.EmptyPdfBodyException;
import com.softwaremagico.tm.pdf.small.SmallCharacterSheet;

public class SmallPdfVisualizationFragment extends PdfVisualizationFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SmallPdfVisualizationFragment newInstance(int index) {
        SmallPdfVisualizationFragment fragment = new SmallPdfVisualizationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View getFragmentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.visualization_pdf_fragment, container, false);
    }

    @Override
    protected byte[] generatePdf() {
        final SmallCharacterSheet characterSheet = new SmallCharacterSheet(CharacterManager.getSelectedCharacter());
        try {
            return (characterSheet.generate());
        } catch (EmptyPdfBodyException e) {
            AdvisorLog.errorMessage(this.getClass().getName(), e);
        } catch (DocumentException e) {
            AdvisorLog.errorMessage(this.getClass().getName(), e);
        } catch (InvalidXmlElementException e) {
            AdvisorLog.errorMessage(this.getClass().getName(), e);
        }
        return new byte[0];
    }
}

package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.ITvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TvService {

    @Autowired
    private ITvRepository iTvRepository;

    public ITvRepository getiTvRepository() {
        return iTvRepository;
    }

    public void setiTvRepository(ITvRepository iTvRepository) {
        this.iTvRepository = iTvRepository;
    }

    public Page<TV> getAll(Pageable pageable){
        return this.iTvRepository.findAll(pageable);
    }

    public Page<TV> findTvByTitle(Pageable pageable, String title){

        Page<TV> tvs = this.iTvRepository.findByTitleContainingIgnoreCase(pageable, title);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public Page<TV> findTvByLanguage(Pageable pageable, String language){

        Page<TV> tvs = this.iTvRepository.findByLanguageLike(pageable, language);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public Page<TV> findTvByDate(Pageable pageable, String date){

        Page<TV> tvs = this.iTvRepository.findByReleaseYearLike(pageable, date);

        if(tvs != null){
            return tvs;
        } else {
            return null;
        }

    }

    public TV updateTv(Long id, TV tv){

        Optional<TV> tvToBeUpdated = this.iTvRepository.findById(id);

        if(tvToBeUpdated != null){

            tvToBeUpdated.get().setId(id);
            tvToBeUpdated.get().setTitle(tv.getTitle());
            tvToBeUpdated.get().setOverview(tv.getOverview());
            tvToBeUpdated.get().setOriginCountry(tv.getOriginCountry());
            tvToBeUpdated.get().setLanguage(tv.getLanguage());
            tvToBeUpdated.get().setReleaseYear(tv.getReleaseYear());
            tvToBeUpdated.get().setRuntime(tv.getRuntime());

            this.iTvRepository.save(tvToBeUpdated.get());

            return tvToBeUpdated.get();
        } else {
            return null;
        }

    }

    public boolean deleteTv(Long id){

        boolean tvExists = this.iTvRepository.existsById(id);
        if(tvExists){
            this.iTvRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
